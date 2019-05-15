package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document.Bill;

@Repository
public interface BillRepository extends MongoRepository<Bill, String> {
	
	/**
	 * Find a promotion by mongo's id
	 * @param id
	 * @return product
	 */
	public Bill findBillById(String id);
	
	/**
	 * Find a promotion by  id
	 * @param id
	 * @return product
	 */
	public Bill findByBillId(int id);
	
	/**
	 * Find the list of product that have the same priceP
	 * @param price
	 * @return list of products
	 */
	public List<Bill> findByValue(double value);

	
}
