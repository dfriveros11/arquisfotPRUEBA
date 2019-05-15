package edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

	@Query("select u from  #{#entityName} u where u.id = ?1")
	public Product findProductByID(long id);
	
	@Query("select u from #{#entityName} u")
	public List<Product> getAllProducts();

}
