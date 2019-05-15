package edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.entity.Bill;

@Repository
public interface BillRepository extends CrudRepository<Bill, Long>{
	
	@Query("select u from  #{#entityName} u where u.id = ?1")
	public Bill findBillByID(long id);
	
	@Query("select u from #{#entityName} u")
	public List<Bill> getAllBills();

}