package edu.uniandes.isis2503.diegodanieldanielajuan.atpostransaction.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.uniandes.isis2503.diegodanieldanielajuan.atpostransaction.entity.Bill;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpostransaction.entity.Pay;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpostransaction.entity.Transaction;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpostransaction.repository.BillRepository;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpostransaction.repository.PayRepository;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpostransaction.repository.TransactionRepository;

@Controller
@RequestMapping("/{applicationName}")
public class TransactionController {
	
	/**
	 * Connection to the Repository of transaction
	 */
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private PayRepository payRepository;
	

	/**
	 * /transactions/create -> create a new transaction and save it in the database.
	 * 
	 * @return a string describing if the transaction is succesfully created or not.
	 */
	@PostMapping("/create")
	@ResponseBody
	public Transaction createTransaction(@Valid @RequestBody Transaction transaction) {
		try {
			return transactionRepository.save(transaction);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * /transactions/update/{id} -> updated the transactions with passed
	 * 
	 * @return A transaction updated
	 */
	@PutMapping("/update/{id}")
	@ResponseBody
	public ResponseEntity<Transaction> updateTransaction(@PathVariable("id") long id, @Valid @RequestBody Transaction transaction) {
		try {
			Transaction oldTransaction = transactionRepository.findTransactionByID(id);
			if (oldTransaction == null) {
				throw new Exception("The Transaction with id: " + id + " doesn´t exist in our database");
			}
			oldTransaction.setValue(transaction.getValue());
			final Transaction updateTransaction = transactionRepository.save(oldTransaction);
			return ResponseEntity.ok(updateTransaction);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * /transactions/getbyid/{id} -> return the transaction having the passed id.
	 * 
	 * @param id The id to search in the database.
	 * @return transaction with the id
	 */
	@GetMapping("/getbyid/{id}")
	@ResponseBody
	public Transaction getTransactionID(@PathVariable("id") long id) {
		try {
			Transaction transaction = transactionRepository.findTransactionByID(id);
			if (transaction == null) {
				throw new Exception("The Transaction with id: " + id + " doesn´t exist in our database");
			}
			return transaction;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /bills/alltransactions-> return all the transactions..
	 * 
	 * @return a empty list.
	 */
	@ResponseBody
	@GetMapping("/alltransactions")
	public List<Transaction> getTransactions() {
		try {
			List<Transaction> transactions = transactionRepository.getAllTransactions();
			return transactions;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /transactions/delete/{id} -> delete the transaction having the passed id.
	 * 
	 * @param id The id for the transactions to delete
	 * @return A string describing if the transaction is succesfully deleted or not.
	 */
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteTransaction(@PathVariable("id") long id) {
		try {
			Transaction transactionDelete = transactionRepository.findTransactionByID(id);
			if (transactionRepository.findTransactionByID(id) == null) {
				throw new Exception("The Transaction with id: " + id + " doesn´t exist in our database");
			}
			transactionRepository.delete(transactionDelete);
		} catch (Exception ex) {
			return "Error deleting the transaction:" + ex.toString();
		}
		return "Transaction succesfully deleted with id : " + id;
	}
	
	
	/* CONNECTION WITH BILL */
	
	@PostMapping("create/{id}/bills")
	@ResponseBody
	public Bill createTransacationBill(@PathVariable("id") long id, @RequestBody @Valid Bill bill) {
		try {
			Transaction transaction = transactionRepository.findTransactionByID(id);
			if (transaction == null) {
				throw new Exception("The transaction with id:  " + id + " doesn't exist");
			}
			Bill billTest = transaction.getBill();
			if (billTest != null) {
				throw new Exception("The transaction with id: " + id + " has already a bill");
			}
			bill.setTransaction(transaction);
			return billRepository.save(bill);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/getbyid/{id}/bills/{idB}")
	@ResponseBody
	public Bill getTransactionBill(@PathVariable("id") long id, @PathVariable("idB") int idB) {
		try {
			Transaction transaction = transactionRepository.findTransactionByID(id);
			if (transaction == null) {
				throw new Exception("The transaction  with id:  " + id + " doesn't exist");
			}
			Bill bill = transaction.getBill();
			if(bill == null) {
				throw new Exception("Bill doesn't exist");
			}
			if (Long.compare(bill.getId(), idB) != 0) {
				throw new Exception("Bill with that id:" + idB + "doesn't exist");
			}
			return bill;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	/* CONNECTION WITH PAY */
	
	@PostMapping("create/{id}/pays")
	@ResponseBody
	public Pay createTransacationPay(@PathVariable("id") long id, @RequestBody @Valid Pay pay) {
		try {
			Transaction transaction = transactionRepository.findTransactionByID(id);
			if (transaction == null) {
				throw new Exception("The transaction with id:  " + id + " doesn't exist");
			}
			Pay payTest = transaction.getPay();
			if (payTest != null) {
				throw new Exception("The transaction with id: " + id + " has already a pay");
			}
			pay.setTransaction(transaction);
			return payRepository.save(pay);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/getbyid/{id}/pays/{idP}")
	@ResponseBody
	public Pay getTransactionPay(@PathVariable("id") long id, @PathVariable("idP") int idP) {
		try {
			Transaction transaction = transactionRepository.findTransactionByID(id);
			if (transaction == null) {
				throw new Exception("The transaction  with id:  " + id + " doesn't exist");
			}
			Pay pay = transaction.getPay();
			if(pay == null) {
				throw new Exception("Pay doesn't exist");
			}
			if (Long.compare(pay.getId(), idP) != 0) {
				throw new Exception("Pay with that id:" + idP + "doesn't exist");
			}
			return pay;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
