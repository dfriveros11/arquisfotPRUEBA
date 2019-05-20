package edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity;

import java.util.List;

import edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.pay.Pay;

public class ProductsAndPay {
	
	private List<IdProduct> idProducts;
	
	private Pay pay;

	public List<IdProduct> getIdProducts() {
		return idProducts;
	}

	public void setIdProducts(List<IdProduct> idProducts) {
		this.idProducts = idProducts;
	}

	public Pay getPay() {
		return pay;
	}

	public void setPay(Pay pay) {
		this.pay = pay;
	}

	
}


