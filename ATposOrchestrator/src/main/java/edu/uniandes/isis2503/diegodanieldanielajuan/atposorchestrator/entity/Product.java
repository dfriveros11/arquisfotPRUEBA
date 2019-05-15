package edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity;

public class Product {
	

	/**
	 * Id of the Product
	 */
	private long id;
	
	private String name;
	
	private double price;
	
	private Promotion promotion;
	
	private Bill bill;
	
	
	public Product(){}
	
	public Product(long id) {
		this.id = id;
	}

	public Product(String name, double price, Promotion promotion, Bill bill) {
		super();
		this.name = name;
		this.price = price;
		this.promotion = promotion;
		this.bill = bill;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
