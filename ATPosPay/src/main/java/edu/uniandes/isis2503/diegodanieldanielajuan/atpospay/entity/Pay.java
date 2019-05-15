package edu.uniandes.isis2503.diegodanieldanielajuan.atpospay.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Pay implements Serializable {

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
	@NotNull
	private Double value;
	
	@JsonManagedReference(value="PayBuyer")  
	@OneToOne(mappedBy = "pay", cascade = CascadeType.ALL)
	private Buyer buyer;
	
	@JsonManagedReference(value="PayMethod")
	@OneToOne(mappedBy = "pay", cascade = CascadeType.ALL)
	private Method method;
	
	public Pay() {}

	public Pay(String name, String description, @NotNull Double value, Buyer buyer, Method method) {
		super();
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

	public void setValue(Double value) {
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
