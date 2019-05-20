package edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity;


import edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.bill.Bill;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.pay.Pay;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.transaction.Transaction;

public class TransactionFinish {
	
	
	private Pay pay;
	
	private Bill bill;
	
	private Transaction transaction;
	
	public TransactionFinish(Pay pay, Bill bill, Transaction transaction) {
		super();
		this.pay = pay;
		this.bill = bill;
		this.transaction = transaction;
	}
	
	public Pay getPay() {
		return pay;
	}
	public void setPay(Pay pay) {
		this.pay = pay;
	}
	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	


}
