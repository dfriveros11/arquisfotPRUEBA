package edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.Bill;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.Promotion;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.TransactionFinish;

@Controller
@RequestMapping("/orchestators")
public class ATposOrchestatorTransaction {
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("unused")
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@PostMapping("/transactions/{idPromotion}")
	@ResponseBody
	public TransactionFinish getPromotion(@PathVariable("idPromotion") long idPromotion, @RequestBody @Valid Bill bill) {
		Promotion promotion = restTemplate.getForObject("http://promotions/promotions/getbyid/" + idPromotion, Promotion.class);
		Bill billAdd = restTemplate.postForObject("http://bills/bills/create", bill, Bill.class);
		TransactionFinish transactionFinish = new TransactionFinish(promotion, billAdd);
		return transactionFinish;
	}

}
