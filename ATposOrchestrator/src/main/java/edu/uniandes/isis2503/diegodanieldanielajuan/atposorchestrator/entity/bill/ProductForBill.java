package edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.bill;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class ProductForBill {

	/**
	 * Id of the Product
	 */
	private long id;
	
	
	private double price;
	
	@JsonManagedReference(value="ProductPomotion") 
	private PromotionForBill promotion;
	
	@JsonBackReference(value="BillProduct")
	private Bill bill;
	
	
	public ProductForBill(){}
	

	public ProductForBill(long id, double price, PromotionForBill promotion, Bill bill) {
		super();
		this.id = id;
		this.price = price;
		this.promotion = promotion;
		this.bill = bill;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public PromotionForBill getPromotion() {
		return promotion;
	}

	public void setPromotion(PromotionForBill promotion) {
		this.promotion = promotion;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}


}
