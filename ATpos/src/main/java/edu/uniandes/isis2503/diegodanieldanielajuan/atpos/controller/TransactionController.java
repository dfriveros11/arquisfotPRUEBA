package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document.Product;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document.Transaction;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.service.TransactionService;


@Controller
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	/**
	 * /transactions/create -> create a new transactions and save it in the database.
	 * 
	 * @return a string describing if the transaction is succesfully created or not.
	 * @throws Exception 
	 */
	@PostMapping("/create")
	@ResponseBody
	public String createTransaction(@RequestBody @Valid Transaction transaction) {
		return transactionService.createTransaction(transaction);
	}

	/**
	 * /transactions/update -> updated the transaction with passed
	 * 
	 * @return A transaction updated
	 */
	@PutMapping("/update")
	@ResponseBody
	public Transaction updateTransaction(@RequestBody @Valid Transaction transaction) {
		return transactionService.updateTransaction(transaction);
	}

	/**
	 * /products/getbyid/{id} -> return the transaction having the passed id.
	 * 
	 * @param id The id to search in the database.
	 * @return transaction with the id
	 */
	@GetMapping("/getbyid/{id}")
	@ResponseBody
	public Transaction getTransactionID(@PathVariable("id") int id) {
		return transactionService.getTransactionID(id);
	}


	/**
	 * /products/allproducts -> return all the bills..
	 * 
	 * @return a empty list.
	 */
	@ResponseBody
	@GetMapping("/alltransactions")
	public List<Transaction> getTransactions() {
		return transactionService.getTransactions();
	}

	/**
	 * /products/delete/{id} -> delete the transaction having the passed id.
	 * 
	 * @param id The id for the bills to delete
	 * @return A string describing if the transaction is succesfully deleted or not.
	 */
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteTransaction(@PathVariable("id") int id) {
		return transactionService.deleteTransaction(id);
	}

	// Relation with product

	@PostMapping("create/{id}/products")
	@ResponseBody
	public String createTransactionProduct(@PathVariable("id") int id, @RequestBody @Valid Product product) {
			return transactionService.createTransactionProduct(id, product);
		}

	@DeleteMapping("/delete/{id}/products/{idP}")
	@ResponseBody
	public String deleteTransactionProduct(@PathVariable("id") int id, @PathVariable("idP") int idP) {
		return transactionService.deleteTransactionProduct(id, idP);
	}

}
