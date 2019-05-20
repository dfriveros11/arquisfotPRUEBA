package edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Bill implements Serializable {

	/**
	 * 
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
	 * Last modified Date
	 */
    @LastModifiedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
	
	/**
	 * Total price of the Bill
	 */
	@NotNull
	private double value;
	
	@JsonManagedReference(value="BillRefund")  
	@OneToOne(mappedBy = "bill", cascade = CascadeType.ALL)
	private Refund refund;
	
	@JsonManagedReference(value="BillProduct")
	@OneToMany(fetch=FetchType.LAZY, mappedBy="bill")
	private List<Product> products;
	
	public Bill() {}

	
	public Bill(double value, Refund refund, List<Product> products) {
		super();
		this.value = value;
		this.refund = refund;
		this.products = products;
	}

	public long getId() {
		return id;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * 
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * 
	 * @param value set value
	 */
	public void setValue(double value) {
		this.value = value;
	}


	public Refund getRefund() {
		return refund;
	}


	public void setRefund(Refund refund) {
		this.refund = refund;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
