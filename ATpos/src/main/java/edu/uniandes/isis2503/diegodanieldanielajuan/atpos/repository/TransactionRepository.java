package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

	/**
	 * Find a transaction by mongo's id
	 * @param id
	 * @return transaction
	 */
	public Transaction findTransactionById(String id);
	
	/**
	 * Find a transaction by  id
	 * @param id
	 * @return transaction
	 */
	@CacheEvict(value="transaction", allEntries=true, key="id")
	public Transaction findByTransactionId(int id);
	
	/**
	 * Find the list of transactions that have a specific product
	 * @param product
	 * @return list of transactions
	 */
	@Query(value= "{'productId' :?0}")
	public List<Transaction> findTransactionWithProduct(int productId);
		
	@DeleteQuery(value="{'transactionId' :?0}")
	public long deleteProduct(int productPromotionId);

}
