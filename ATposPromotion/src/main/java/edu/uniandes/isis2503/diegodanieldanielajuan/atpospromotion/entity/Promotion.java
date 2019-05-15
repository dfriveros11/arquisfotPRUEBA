package edu.uniandes.isis2503.diegodanieldanielajuan.atpospromotion.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("promotion")
public class Promotion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -84686610694258704L;
	
	@Id
	private long productId;
	
	private long promotionId;
	
	private double value;

	public Promotion(long productId, long promotionId, double value) {
		super();
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
