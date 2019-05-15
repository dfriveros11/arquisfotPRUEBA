package edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
public class Promotion implements Serializable{
	
	
	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 4992394792076356583L;

	/**
	 * Id of the Promotion
	 */
	@Id
	private long id;
	
	/**
	 * Product's promotion
	 */
	@NotNull(message="The promotion must have a value")
	private double value;
	
	@JsonBackReference(value="ProductPomotion") 
	@OneToOne(fetch=FetchType.LAZY)
    @MapsId
    private Product product;
	
	/**
	 * Empty Constructor
	 */
	public Promotion() {}
	
	public Promotion(long id) {
		this.id = id;
	}
	
	/**
	 * Constructor
	 */
	public Promotion(double value, Product product) {
		this.value = value;
		this.product = product;
	}

	public long getId() {
		return id;
	}
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


}
