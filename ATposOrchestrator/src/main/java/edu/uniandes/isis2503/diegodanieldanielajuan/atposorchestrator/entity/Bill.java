package edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;



public class Bill {


	/**
	 * Id of the Entity
	 */
	private long id;
	
	/**
	 * Creation Date
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    
    /**
	 * Last modified Date
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifiedDate;
	
	/**
	 * Total price of the Bill
	 */
	private Double value;
	
	private Refund refund;
	
	private List<Product> products;
	
	public Bill() {}

	
	public Bill(Double value, Refund refund, List<Product> products) {
		super();
		this.value = value;
		this.refund = refund;
		this.products = products;
	}

	public long getId() {
		return id;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * 
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * 
	 * @param value set value
	 */
	public void setValue(Double value) {
		this.value = value;
	}


	public Refund getRefund() {
		return refund;
	}


	public void setRefund(Refund refund) {
		this.refund = refund;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
