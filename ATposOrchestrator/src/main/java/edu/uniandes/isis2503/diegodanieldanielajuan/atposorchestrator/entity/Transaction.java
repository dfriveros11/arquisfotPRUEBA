package edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity;

import java.util.Date;

public class Transaction{

	/**
	 * Id of the Entity
	 */
	private long id;
	
	/**
	 * Creation Date
	 */
	private Date createdDate;
	
    
	/**
	 * Total price of the Bill
	 */
	private Double value;
	
	private Bill bill;
	
	private Pay pay;
	
	
	public Transaction() {}
	
	public Transaction(long id) {
		this.id = id;
	}

	
	public Transaction(Double value, Bill bill, Pay pay) {
		super();
		this.value = value;
		this.bill = bill;
		this.pay = pay;
	}

	public long getId() {
		return id;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Pay getPay() {
		return pay;
	}

	public void setPay(Pay pay) {
		this.pay = pay;
	}

}
