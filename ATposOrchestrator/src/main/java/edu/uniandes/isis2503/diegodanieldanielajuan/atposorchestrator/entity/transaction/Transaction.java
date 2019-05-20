package edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.transaction;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;


public class Transaction{

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
	 * Total price of the Bill
	 */
	private Double value;
	
	@JsonManagedReference(value="TransactionBill")  
	private BillForTransaction bill;
	
	@JsonManagedReference(value="TransactionPay")  
	private PayForTransaction pay;
	
	
	public Transaction() {}
	
	public Transaction(long id) {
		this.id = id;
	}

	
	public Transaction(Double value, BillForTransaction bill, PayForTransaction pay) {
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

	public BillForTransaction getBill() {
		return bill;
	}

	public void setBill(BillForTransaction bill) {
		this.bill = bill;
	}

	public PayForTransaction getPay() {
		return pay;
	}

	public void setPay(PayForTransaction pay) {
		this.pay = pay;
	}

}
