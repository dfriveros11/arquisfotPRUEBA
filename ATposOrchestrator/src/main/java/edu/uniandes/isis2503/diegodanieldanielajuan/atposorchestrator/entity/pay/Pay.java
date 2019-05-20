package edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.pay;

import com.fasterxml.jackson.annotation.JsonManagedReference;

public class Pay {


	/**
	 * Id of the Entity
	 */
	private long id;
	
	/**
	 * Name of the Pay
	 */
    private String name;
    
    /**
     * Decription of Pay
     */
    private String description;
       
	/**
	 * Total price of the Pay
	 */
	private double value;
	
	@JsonManagedReference(value="PayBuyer")  
	private Buyer buyer;
	
	@JsonManagedReference(value="PayMethod")
	private Method method;
	
	public Pay() {}

	public Pay(long id,String name, String description, double value, Buyer buyer, Method method) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.value = value;
		this.buyer = buyer;
		this.method = method;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	

}
