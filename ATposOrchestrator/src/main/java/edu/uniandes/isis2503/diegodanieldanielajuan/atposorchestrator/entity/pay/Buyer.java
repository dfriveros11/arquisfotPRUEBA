package edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.pay;


import com.fasterxml.jackson.annotation.JsonBackReference;

public class Buyer{

	
	/**
	 * Id of the Entity
	 */
	private long id;
	
   
    /**
	 * Name of the Buyer
	 */
	private String name;
    
	/**
	 * Email of the Buyer
	 */
	private String email;
	
	
	@JsonBackReference(value="PayBuyer")  
    private Pay pay;
	
	
	public Buyer() {}
	
	public Buyer(long id) {
		this.id = id;
	}

	public Buyer(String name, String email, Pay pay) {
		super();
		this.name = name;
		this.email = email;
		this.pay = pay;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Pay getPay() {
		return pay;
	}

	public void setPay(Pay pay) {
		this.pay = pay;
	}

	

}
