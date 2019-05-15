package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document.Buyer;

public interface BuyerRepository extends MongoRepository<Buyer, String>{
	
	/**
	 * Find a product by mongo id
	 * @param id
	 * @return product
	 */
	public Buyer findBuyerById(String id);
	
	/**
	 * Find a product by id
	 * @param id
	 * @return product
	 */
	public Buyer findByBuyerId(int id);
	
	/**
	 * Find the list of product that have the same price
	 * @param price
	 * @return list of products
	 */
	public List<Buyer> findByName(String name);
	

}
