package edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Product implements Serializable {
	
	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 6287805956991986743L;

	/**
	 * Id of the Product
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private double price;
	
	@JsonManagedReference(value="ProductPomotion") 
	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
	private Promotion promotion;
	
	@JsonBackReference(value="BillProduct")
	@ManyToOne(fetch=FetchType.EAGER)
	private Bill bill;
	
	
	public Product(){}
	
	public Product(long id) {
		this.id = id;
	}

	public Product(String name, double price, Promotion promotion, Bill bill) {
		super();
		this.name = name;
		this.price = price;
		this.promotion = promotion;
		this.bill = bill;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}


}
