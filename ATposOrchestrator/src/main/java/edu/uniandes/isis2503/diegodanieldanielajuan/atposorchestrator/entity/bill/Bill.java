package edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.bill;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class Bill {


	/**
	 * Id of the Entity
	 */
	private long id;
	
	/**
	 * Creation Date
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    
    /**
	 * Last modified Date
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifiedDate;
	
	/**
	 * Total price of the Bill
	 */
	private double value;
	
	@JsonManagedReference(value="BillRefund")  
	private Refund refund;
	
	@JsonManagedReference(value="BillProduct")
	private List<ProductForBill> products;
	
	public Bill() {}

	
	public Bill(double value, Refund refund, List<ProductForBill> products) {
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
	public double getValue() {
		return value;
	}

	/**
	 * 
	 * @param value set value
	 */
	public void setValue(double value) {
		this.value = value;
	}


	public Refund getRefund() {
		return refund;
	}


	public void setRefund(Refund refund) {
		this.refund = refund;
	}


	public List<ProductForBill> getProducts() {
		return products;
	}


	public void setProducts(List<ProductForBill> products) {
		this.products = products;
	}

}
