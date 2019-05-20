package edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.bill;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Refund{

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
	 * Description of the refund
	 */
	private String description;
	
	
	@JsonBackReference(value="BillRefund")  
    private Bill bill;
	
	
	public Refund() {}
	
	public Refund(long id) {
		this.id = id;
	}

	
	public Refund(String description, Bill bill) {
		super();
		this.description = description;
		this.bill = bill;
	}
	
	 
    /**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

}
