package edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.transaction;

import com.fasterxml.jackson.annotation.JsonBackReference;



public class PayForTransaction {

	/**
	 * Id of the Entity
	 */
	private long id;
	
	private String name;
	
	private String description;
	
	@JsonBackReference(value="TransactionPay")  
    private Transaction transaction;
	
	public PayForTransaction() {}

	public PayForTransaction(long id) {
		this.id = id;
	}
	
	public PayForTransaction(String name, String description, Transaction transaction) {
		this.name = name;
		this.description = description;
		this.transaction = transaction;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
}
