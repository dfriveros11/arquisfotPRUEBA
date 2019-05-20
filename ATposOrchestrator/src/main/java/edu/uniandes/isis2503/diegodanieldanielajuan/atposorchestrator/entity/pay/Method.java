package edu.uniandes.isis2503.diegodanieldanielajuan.atposorchestrator.entity.pay;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class Method  {
	
	/**
	 * Id of the Product
	 */
	private long id;
	
	private String name;
	
	private String description;

	
	@JsonBackReference(value="PayMethod")
	private Pay pay;
	
	
	public Method(){}


	public Method(String name, String description, Pay pay) {
		super();
		this.name = name;
		this.description = description;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Pay getPay() {
		return pay;
	}


	public void setPay(Pay bill) {
		this.pay = bill;
	}
	


}
