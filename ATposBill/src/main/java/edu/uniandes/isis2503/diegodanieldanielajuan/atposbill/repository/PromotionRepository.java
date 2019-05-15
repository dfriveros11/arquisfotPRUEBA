package edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.entity.Promotion;

@Repository
public interface PromotionRepository extends CrudRepository<Promotion, Long>{
	
	/**
	 * Find a promotion by  id
	 * @param id
	 * @return product
	 */
	@Query("select u from  #{#entityName} u where u.id = ?1")
	public Promotion findPromotionByID(long id);
	
	@Query("select u from #{#entityName} u")
	public List<Promotion> getAllPromotions();
	
	/**
	 * Find the list of product that have the same promotion
	 * @param price
	 * @return list of products
	 */
	public List<Promotion> findByValue(double value);
}
