package edu.uniandes.isis2503.diegodanieldanielajuan.atpostransaction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.uniandes.isis2503.diegodanieldanielajuan.atpostransaction.entity.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

	@Query("select u from  #{#entityName} u where u.id = ?1")
	public Transaction findTransactionByID(long id);
	
	@Query("select u from #{#entityName} u")
	public List<Transaction> getAllTransactions();
}
