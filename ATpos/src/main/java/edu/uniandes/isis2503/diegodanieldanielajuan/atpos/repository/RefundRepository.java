package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document.Refund;

public interface RefundRepository extends MongoRepository<Refund, String>{

	/**
	 * Find a refund by mongo id
	 * @param id
	 * @return product
	 */
	public Refund findRefundById(String id);
	
	/**
	 * Find a refund by id
	 * @param id
	 * @return product
	 */
	public Refund findByRefundId(int id);
	
	/**
	 * Find the list of refunds by creation date
	 * @param date. date of creation
	 * @return list of refunds
	 */
	public List<Refund> findByCreationDate(Date date);
	
	/**
	 * Find the refunds by description
	 * @param description. The description of the refund
	 * @return
	 */
	public List<Refund> findRefundByDescription(String description);

}
