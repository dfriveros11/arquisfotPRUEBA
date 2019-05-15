package edu.uniandes.isis2503.diegodanieldanielajuan.atpospay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.uniandes.isis2503.diegodanieldanielajuan.atpospay.entity.Pay;

@Repository
public interface PayRepository extends CrudRepository<Pay, Long>{
	
	@Query("select u from  #{#entityName} u where u.id = ?1")
	public Pay findPayByID(long id);
	
	@Query("select u from #{#entityName} u")
	public List<Pay> getAllPays();

}