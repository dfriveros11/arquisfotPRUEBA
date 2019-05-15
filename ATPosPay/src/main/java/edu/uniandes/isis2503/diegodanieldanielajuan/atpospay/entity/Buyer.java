package edu.uniandes.isis2503.diegodanieldanielajuan.atpospay.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Buyer implements Serializable{

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
	 * Name of the Buyer
	 */
	private String name;
    
	/**
	 * Email of the Buyer
	 */
	private String email;
	
	
	@JsonBackReference(value="PayBuyer")  
	@OneToOne(fetch=FetchType.LAZY)
    @MapsId
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
