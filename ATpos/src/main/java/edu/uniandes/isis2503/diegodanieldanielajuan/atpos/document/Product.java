package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonBackReference;


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
	
	@Nullable
	@JsonBackReference(value="productProductPromotion")
	@DBRef(lazy = false)
	private ProductPromotion productPromotion;
	
	//@JsonIgnore
	@DBRef(lazy = false)
	private List<Transaction> transactions;
	
	/**
	 * Empty Constructor
	 */
	public Product() {}
	
	/**
	 * Constructor
	 */
	public Product(int productId, double price, ProductPromotion productPromotion) {
		this.productId = productId;
		this.price = price;
		this.productPromotion = productPromotion;
		this.transactions = new LinkedList<Transaction>();
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

	public ProductPromotion getProductPromotion() {
		return productPromotion;
	}

	public void setProductPromotion(ProductPromotion productPromotion) {
		this.productPromotion = productPromotion;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}


}
