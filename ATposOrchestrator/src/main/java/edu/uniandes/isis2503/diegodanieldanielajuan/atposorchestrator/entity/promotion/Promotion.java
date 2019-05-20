package edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.promotion;


public class Promotion {

	private long productId;
	
	private long promotionId;
	
	private double value;
	
	public Promotion() {}

	public Promotion(long productId, long promotionId, double value) {
		this.productId = productId;
		this.promotionId = promotionId;
		this.value = value;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(long promotionId) {
		this.promotionId = promotionId;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
}
