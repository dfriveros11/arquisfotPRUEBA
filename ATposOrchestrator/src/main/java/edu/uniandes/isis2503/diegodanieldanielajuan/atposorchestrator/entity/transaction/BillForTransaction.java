package edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.transaction;


import com.fasterxml.jackson.annotation.JsonBackReference;




public class BillForTransaction  {


	/**
	 * Id of the Entity
	 */
	private long id;
	
	/**
	 * Total price of the Bill
	 */
	private Double value;
	
	
	@JsonBackReference(value="TransactionBill")  
    private Transaction transaction;
	
	public BillForTransaction() {}
	
	public BillForTransaction(long id) {
		this.id = id;
	}

	
	public BillForTransaction(Double value, Transaction transaction) {
		super();
		this.value = value;
		this.transaction = transaction;
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

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}
