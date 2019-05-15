package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document.ProductPromotion;

@Repository
public interface ProductPromotionRepository extends MongoRepository<ProductPromotion, String> {
	
	/**
	 * Find a promotion by mongo's id
	 * @param id
	 * @return product
	 */
	public ProductPromotion findProductPromotionById(String id);
	
	/**
	 * Find a promotion by  id
	 * @param id
	 * @return product
	 */
	@CacheEvict(value="productsPromotion", allEntries=true, key="id")
	public ProductPromotion findByProductPromotionId(int id);
	
	/**
	 * Find the list of product that have the same priceP
	 * @param price
	 * @return list of products
	 */
	public List<ProductPromotion> findByPromotion(double promotion);
	
	/**
	 * Find if the product with the id pass has a promotion
	 * @param productPromotion
	 * @return
	 */
	@Query(value="{'productPromotionId' :?0, 'products.productId' : ?1}", fields="{'productPromotionId':1, 'promotion':1, 'products._id' :1, 'products.productId' :1, 'products.price': 1}")
	public ProductPromotion findPromotion(int productPromotionId, int productId);
	
	@DeleteQuery(value="{'productPromotionId' :?0}")
	public long deleteProduct(int productPromotionId);

}
