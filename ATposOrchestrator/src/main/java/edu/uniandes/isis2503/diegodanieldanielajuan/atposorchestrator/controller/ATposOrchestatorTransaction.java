package edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.controller;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.IdProduct;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.ProductsAndPay;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.TransactionFinish;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.bill.Bill;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.bill.ProductForBill;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.bill.PromotionForBill;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.pay.Pay;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.product.Product;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.promotion.Promotion;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.transaction.BillForTransaction;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.transaction.PayForTransaction;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.transaction.Transaction;

@Controller
@RequestMapping("/orchestators")
public class ATposOrchestatorTransaction {
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("unused")
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@PostMapping("/transactions")
	@ResponseBody
	public TransactionFinish getPromotion(@RequestBody @Valid ProductsAndPay productsAndPay){
		List<IdProduct> idProducts = productsAndPay.getIdProducts();
		List<ProductForBill> products = new LinkedList<ProductForBill>();
		double value = 0.0;
		for (IdProduct idProduct : idProducts) {
			long idPro = idProduct.getId();
			Product product = restTemplate.getForObject("http://products/products/getbyid/" + idPro, Product.class);
			double productCost = product.getPrice();
			Promotion promotion = restTemplate.getForObject("http://promotions/promotions/getbyid/" + idPro, Promotion.class);
			ProductForBill productForBill = new ProductForBill(product.getProductId(), product.getPrice(), null, null);
			if(promotion != null) {
				PromotionForBill promotionForBill = new PromotionForBill(promotion.getPromotionId(),  promotion.getValue(), productForBill);
				productForBill.setPromotion(promotionForBill);
				productCost -= promotion.getValue();
			}
			products.add(productForBill);
			value += productCost;
		}
		Pay pay = productsAndPay.getPay();
		pay.setValue(value);
		Pay payVerifie = restTemplate.postForObject("http://pays/pays/create", pay, Pay.class);
		Bill bill = new Bill();
		bill.setProducts(products);
		bill.setValue(value);
		Bill billAdd = restTemplate.postForObject("http://bills/bills/create", bill, Bill.class);
		Transaction transactionAdd = createTransaction(value, pay);
		TransactionFinish transactionFinish = new TransactionFinish(payVerifie, billAdd, transactionAdd);
		return transactionFinish;
	}

	private Transaction createTransaction(double value, Pay pay) {
		Transaction transaction = new Transaction();
		BillForTransaction billForTransaction = new BillForTransaction();
		billForTransaction.setTransaction(transaction);
		billForTransaction.setValue(value);
		transaction.setBill(billForTransaction);
		PayForTransaction payForTransaction = new PayForTransaction();
		payForTransaction.setName(pay.getName());
		payForTransaction.setDescription(pay.getDescription());
		payForTransaction.setTransaction(transaction);
		transaction.setPay(payForTransaction);
		transaction.setValue(value);
		Transaction transactionAdd = restTemplate.postForObject("http://transactions/transactions/create", transaction, Transaction.class);
		return transactionAdd;
	}

}
