package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Document
public class Bill implements Serializable{

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -1086140112755317231L;
	
	/**
	 * Mongo Product's ID
	 */
	@Id
	private String id;
	
	/**
	 * Bill's ID
	 */
	@Indexed
	private int billId;
	
	/**
	 * Bill's value
	 */
	@NotNull(message="The bill must have a value")
	private double value;
	
	/**
	 * Bills have a refund
	 */
	@JsonBackReference(value = "BillRefund")
	private Refund refund;

	
	/**
	 * Constructor
	 */
	public Bill() {}
	
	/**
	 * Constructor
	 * @param id
	 * @param billId
	 * @param value
	 */
	public Bill(String id, int billId, @NotNull(message = "The bill must have a value") double value, Refund refund) {
		super();
		this.id = id;
		this.billId = billId;
		this.value = value;
		this.refund = refund;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Refund getRefund() {
		return refund;
	}

	public void setRefund(Refund refund) {
		this.refund = refund;
	}

	
}
