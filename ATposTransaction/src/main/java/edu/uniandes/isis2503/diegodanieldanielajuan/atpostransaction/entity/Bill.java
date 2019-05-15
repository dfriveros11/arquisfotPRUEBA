package edu.uniandes.isis2503.diegodanieldanielajuan.atpostransaction.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;




@Entity
public class Bill implements Serializable {

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -476497477076371992L;

	/**
	 * Id of the Entity
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/**
	 * Total price of the Bill
	 */
	@NotNull
	private Double value;
	
	
	@JsonBackReference(value="TransactionBill")  
	@OneToOne(fetch=FetchType.LAZY)
    @MapsId
    private Transaction transaction;
	
	public Bill() {}
	
	public Bill(long id) {
		this.id = id;
	}

	
	public Bill(Double value, Transaction transaction) {
		super();
		this.value = value;
		this.transaction = transaction;
	}

	public long getId() {
		return id;
	}

	/**
	 * 
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * 
	 * @param value set value
	 */
	public void setValue(Double value) {
		this.value = value;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}
