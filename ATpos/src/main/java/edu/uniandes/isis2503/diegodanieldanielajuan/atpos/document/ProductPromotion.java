  
package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Document
public class ProductPromotion implements Serializable{

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -1942486597262358119L;
	
	/**
	 * ProductPromotion's ID
	 */
	@Id
	private String id;
	
	/**
	 * Product's ID
	 */
	@Indexed
	private int productPromotionId;
	
	/**
	 * Product to apply the promotion
	 */
	@JsonManagedReference(value="productProductPromotion")
	@DBRef(lazy = false)
	private List<Product> products;
	
	/**
	 * Product's promotion
	 */
	@NotNull(message="The product must have a promotion")
	private double promotion;
	
	/**
	 * Empty Constructor
	 */
	public ProductPromotion() {}
	
	/**
	 * Constructor
	 */
	public ProductPromotion(int productPromotionId, double promotion) {
		this.productPromotionId = productPromotionId;
		this.products = new LinkedList<Product>();
		this.promotion = promotion;
	}

	public String getId() {
		return id;
	}

	public int getProductPromotionId() {
		return productPromotionId;
	}

	public double getPromotion() {
		return promotion;
	}

	public void setPromotion(double promotion) {
		this.promotion = promotion;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}