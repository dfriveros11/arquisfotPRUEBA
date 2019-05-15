package edu.uniandes.isis2503.diegodanieldanielajuan.atpostransaction.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Transaction implements Serializable{

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
	 * Creation Date
	 */
	@CreatedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
    
	/**
	 * Total price of the Bill
	 */
	@NotNull
	private Double value;
	
	@JsonManagedReference(value="TransactionBill")  
	@OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL)
	private Bill bill;
	
	@JsonManagedReference(value="TransactionPay")  
	@OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL)
	private Pay pay;
	
	
	public Transaction() {}
	
	public Transaction(long id) {
		this.id = id;
	}

	
	public Transaction(Double value, Bill bill, Pay pay) {
		super();
		this.value = value;
		this.bill = bill;
		this.pay = pay;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Pay getPay() {
		return pay;
	}

	public void setPay(Pay pay) {
		this.pay = pay;
	}

}
