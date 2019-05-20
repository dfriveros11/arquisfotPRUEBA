package edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.bill;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;



public class PromotionForBill{
	
	/**
	 * Id of the Promotion
	 */
	private long id;
	
	
	/**
	 * Product's promotion
	 */
	@NotNull(message="The promotion must have a value")
	private double value;
	
	@JsonBackReference(value="ProductPomotion") 
    private ProductForBill product;
	
	/**
	 * Empty Constructor
	 */
	public PromotionForBill() {}
	
	/**
	 * Constructor
	 */
	public PromotionForBill(long id, double value, ProductForBill product) {
		this.id = id;
		this.value = value;
		this.product = product;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public ProductForBill getProduct() {
		return product;
	}

	public void setProduct(ProductForBill product) {
		this.product = product;
	}


}
