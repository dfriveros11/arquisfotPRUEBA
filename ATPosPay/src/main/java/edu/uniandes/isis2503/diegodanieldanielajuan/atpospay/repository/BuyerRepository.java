package edu.uniandes.isis2503.diegodanieldanielajuan.atpospay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.uniandes.isis2503.diegodanieldanielajuan.atpospay.entity.Buyer;

@Repository
public interface BuyerRepository extends CrudRepository<Buyer, Long>{
	
	@Query("select u from  #{#entityName} u where u.id = ?1")
	public Buyer findBuyerByID(long id);
	
	@Query("select u from #{#entityName} u")
	public List<Buyer> getAllBuyers();

}
