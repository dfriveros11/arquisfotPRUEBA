package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Document
public class Refund implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Id of the refund
	 */
	@Id
	private String id;
	
	/**
	 * Refund's ID
	 */
	@Indexed
	private int refundId;

	/**
	 * Description of the refund
	 */
	private String description;

	/**
	 * Date of the creation of the refund
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creationDate;

	/**
	 * Value of the refund
	 */
	private int value;
	
	@Nullable
	@JsonManagedReference(value = "BillRefund")
	private Bill bill;

	public Refund() {
	}

	/**
	 * Constructor of the refund
	 * 
	 * @param description
	 */
	public Refund(int refundId, String description, Date creationDate, int value, Bill bill) {
		this.refundId = refundId;
		this.description = description;
		this.creationDate = creationDate;
		this.value = value;
		this.bill = bill;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public int getRefundId() {
		return refundId;
	}

	public void setRefundId(int refundId) {
		this.refundId = refundId;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}


}
