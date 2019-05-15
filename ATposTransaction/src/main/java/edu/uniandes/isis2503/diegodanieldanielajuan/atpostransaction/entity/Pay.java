package edu.uniandes.isis2503.diegodanieldanielajuan.atpostransaction.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
public class Pay implements Serializable{

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -7667937900108482164L;
	
	/**
	 * Id of the Entity
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String description;
	
	@JsonBackReference(value="TransactionPay")  
	@OneToOne(fetch=FetchType.LAZY)
    @MapsId
    private Transaction transaction;
	
	public Pay() {}

	public Pay(long id) {
		this.id = id;
	}
	
	public Pay(String name, String description, Transaction transaction) {
		this.name = name;
		this.description = description;
		this.transaction = transaction;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
}
