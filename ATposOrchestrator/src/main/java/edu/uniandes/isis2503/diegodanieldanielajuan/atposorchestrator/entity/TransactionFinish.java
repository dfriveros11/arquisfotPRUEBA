package edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity;

public class TransactionFinish {
	
	private Promotion promotion;
	private Bill bill;

	public TransactionFinish(Promotion promotion, Bill bill) {
		this.promotion = promotion;
		this.bill = bill;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

}
