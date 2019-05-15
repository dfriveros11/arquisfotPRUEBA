package edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Refund implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4070162456517357128L;
	
	
	/**
	 * Id of the Entity
	 */
	@Id
	private long id;
	
	/**
	 * Creation Date
	 */
    @CreatedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

   
	/**
	 * Description of the refund
	 */
	private String description;
	
	
	@JsonBackReference(value="BillRefund")  
	@OneToOne(fetch=FetchType.LAZY)
    @MapsId
    private Bill bill;
	
	
	public Refund() {}
	
	public Refund(long id) {
		this.id = id;
	}

	
	public Refund(String description, Bill bill) {
		super();
		this.description = description;
		this.bill = bill;
	}
	
	 
    /**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

}
