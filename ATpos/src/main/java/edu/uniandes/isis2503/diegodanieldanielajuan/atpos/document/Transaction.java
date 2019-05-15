package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Document
public class Transaction implements Serializable{

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
	private int transactionId;
	
	/**
	 * Value
	 */
	@NotNull(message="The transaction must have value")
	private double value;
	
	/**
	 * List of products included in the transaction
	 */
	@DBRef(lazy = false)
	private List<Product> products;
	
	@JsonBackReference(value="transactionBuyer")
	private Buyer buyer;
	/**
	 * Empty Constructor
	 */
	public Transaction() {}

	/**
	 * Constructor
	 */
	
	
	public String getId() {
		return id;
	}

	public Transaction(int pTransactionId, double value) {
		this.transactionId = pTransactionId;
		this.value = value;
		this.products = new LinkedList<Product>();
	}

	public int getTransactionId() {
		return transactionId;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public void addProduct(Product product) {
		this.products.add(product);
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	
	@Override
	public String toString(){
		String men = "Id:"+transactionId + "Value:"+value + "products";
		if (products != null) {
			for(int i=0; i<products.size();i++){
				men+= products.get(i).getProductId()+",";
			}
		}
		return men;
	}

}
