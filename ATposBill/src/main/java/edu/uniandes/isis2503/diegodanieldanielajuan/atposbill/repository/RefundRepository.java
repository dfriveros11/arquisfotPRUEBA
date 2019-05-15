package edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.entity.Refund;

@Repository
public interface RefundRepository extends CrudRepository<Refund, Long>{
	
	@Query("select u from  #{#entityName} u where u.id = ?1")
	public Refund findRefundByID(long id);
	
	@Query("select u from #{#entityName} u")
	public List<Refund> getAllRefunds();

}
