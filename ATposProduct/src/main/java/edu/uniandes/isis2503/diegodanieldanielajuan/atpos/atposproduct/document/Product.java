package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.atposproduct.document;

import java.io.Serializable;


import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Product implements Serializable{

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -1086140112755317231L;
	
	/**
	 * Mongo Product's ID
	 */
	@Id
	private String id;
	
	/**
	 * Product's ID
	 */
	@Indexed
	private int productId;
	
	/**
	 * Product's cost
	 */
	@NotNull(message="The product must have a price")
	private double price;
	
	
	/**
	 * Empty Constructor
	 */
	public Product() {}
	
	/**
	 * Constructor
	 */
	public Product(int productId, double price) {
		this.productId = productId;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public int getProductId() {
		return productId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}



}
