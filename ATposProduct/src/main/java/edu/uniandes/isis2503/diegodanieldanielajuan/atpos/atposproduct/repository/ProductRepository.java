package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.atposproduct.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.atposproduct.document.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>{
	
	/**
	 * Find a product by mongo id
	 * @param id
	 * @return product
	 */
	public Product findProductById(String id);
	
	/**
	 * Find a product by id
	 * @param id
	 * @return product
	 */
	public Product findByProductId(int id);
	
	/**
	 * Find the list of product that have the same price
	 * @param price
	 * @return list of products
	 */
	public List<Product> findByPrice(double price);


}
