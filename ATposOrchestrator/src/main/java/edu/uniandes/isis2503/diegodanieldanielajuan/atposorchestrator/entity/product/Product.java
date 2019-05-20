package edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.product;


public class Product {
	

	private String id;
	/**
	 * Id of the Product
	 */
	private long productId;
	
	
	private double price;
	
	
	public Product(){}
	
	public Product(long productId) {
		this.productId = productId;
	}

	public Product(double price) {
		super();
		this.price = price;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
