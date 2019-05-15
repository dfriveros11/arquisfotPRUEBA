package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class Buyer implements Serializable{
	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -1086140112755317231L;
	
	/**
	 * Mongo Buyer's ID
	 */
	@Id
	private String id;
	
	/**
	 * Buyer's ID
	 */
	@Indexed(unique = false)
	private int buyerId;
	
	/**
	 * Buyer's cost
	 */
	@NotNull(message="The buyer must have a name")
	private String name;
	
	/**
	 * points
	 */
	private int points;
	
	/**
	 * address
	 */
	private String address;
	
	/**
	 * email
	 */
	@JsonFormat(pattern="^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")
	private String email;
	
	
	private String phoneNumber;
	
	@JsonManagedReference(value="transactionBuyer")
	private List<Transaction> transactions;
	
//	@Nullable
//	@JsonBackReference
//	private ProductPromotion productPromotion;
	
	/**
	 * Empty Constructor
	 */
	public Buyer() {}
	
	/**
	 * Constructor
	 */
	public Buyer(int buyerId, @NotNull(message = "The buyer must have a name") String name, int points, String address, String email, String phoneNumber) {
		this.buyerId = buyerId;
		this.name = name;
		this.points = points;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.transactions = new LinkedList<Transaction>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
	
}
