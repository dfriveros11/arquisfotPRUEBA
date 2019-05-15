package edu.uniandes.isis2503.diegodanieldanielajuan.atpostransaction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.uniandes.isis2503.diegodanieldanielajuan.atpostransaction.entity.Pay;

public interface PayRepository extends CrudRepository<Pay, Long> {

	@Query("select u from  #{#entityName} u where u.id = ?1")
	public Pay findPayByID(long id);
	
	@Query("select u from #{#entityName} u")
	public List<Pay> getAllPays();

}
