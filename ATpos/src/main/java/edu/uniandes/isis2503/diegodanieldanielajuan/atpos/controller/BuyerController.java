package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.controller;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document.Buyer;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document.Transaction;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.repository.BuyerRepository;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.repository.TransactionRepository;


@Controller
@RequestMapping("/buyers")
public class BuyerController {

	/**
	 * Connection to the repository of buyer
	 */
	@Autowired
	private BuyerRepository buyerRepository;
	
	/**
	 * Connection to the repository of transaction
	 */
	@Autowired
	private TransactionRepository transactionRepository;

	/**
	 * /productpromotions/create -> create a new productpromotions and save it in the database.
	 * 
	 * @return a string describing if the Buyer is succesfully created or not.
	 */
	@PostMapping("/create")
	@ResponseBody
	public String createBuyer(@RequestBody @Valid Buyer buyer) {
		try {
			Buyer buyerTest = buyerRepository.findByBuyerId(buyer.getBuyerId());
			if(buyerTest != null) {
				throw new DuplicateKeyException("The Buyer's id exist");
			}
			buyerRepository.insert(buyer);
		} catch (Exception ex) {
			return "Error creating the product: " + ex.toString();
		}
		return "Buyer succesfully created with id: " + buyer.getBuyerId();
	}

	/**
	 * /productpromotions/update -> updated the Buyer with passed
	 * 
	 * @return A Buyer updated
	 */
	@PutMapping("/update")
	@ResponseBody
	public Buyer updateBuyer(@RequestBody @Valid Buyer buyer) {
		try {

			Buyer oldBuyer = buyerRepository.findByBuyerId(buyer.getBuyerId());
			if (oldBuyer == null) {
				throw new Exception("The Product with id: " + buyer.getBuyerId() + " doesn´t exist in our database");
			}
			oldBuyer.setBuyerId(buyer.getBuyerId());
			oldBuyer.setName(buyer.getName());
			oldBuyer.setPoints(buyer.getPoints());
			oldBuyer.setAddress(buyer.getAddress());
			oldBuyer.setEmail(buyer.getEmail());
			oldBuyer.setPhoneNumber(buyer.getPhoneNumber());
			buyerRepository.save(oldBuyer);
			return oldBuyer;
		} catch (Exception ex) {
			ex.toString();
			return null;
		}
	}

	/**
	 * /products/getbyid/{id} -> return the Buyer having the passed id.
	 * 
	 * @param id The id to search in the database.
	 * @return Buyer with the id
	 */
	@GetMapping("/getbyid/{id}")
	@ResponseBody
	public Buyer getBuyerID(@PathVariable("id") int id) {
		try {
			Buyer buyer = buyerRepository.findByBuyerId(id);
			if (buyer == null) {
				throw new Exception("The Product with id: " + id + " doesn´t exist in our database");
			}
			return buyer;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /products/allproducts -> return all the bills..
	 * 
	 * @return a empty list.
	 */
	@ResponseBody
	@GetMapping("/allbuyers")
	public List<Buyer> getBuyers() {
		try {
			List<Buyer> buyers = buyerRepository.findAll();
			return buyers;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /products/delete/{id} -> delete the Buyer having the passed id.
	 * 
	 * @param id The id for the bills to delete
	 * @return A string describing if the Buyer is succesfully deleted or not.
	 */
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteBuyer(@PathVariable("id") int id) {
		try {
			Buyer buyer = buyerRepository.findByBuyerId(id);
			buyerRepository.delete(buyer);
		} catch (Exception ex) {
			return "Error deleting the Product:" + ex.toString();
		}
		return "Product succesfully deleted with id : " + id;
	}
	
	@PostMapping("/create/{id}/transaction")
	@ResponseBody
	public String createTransactionByBuyer(@PathVariable("id") int buyerId, @RequestBody @Valid Transaction transaction) {
		try {
			Transaction trans = transactionRepository.findByTransactionId(transaction.getTransactionId());
			if(trans!=null) {
				return "Error transaction already exists";
			}
			Buyer buyer = buyerRepository.findByBuyerId(buyerId);
			transactionRepository.insert(transaction);
			if(buyer != null) {
				List<Transaction> transacciones = buyer.getTransactions();
				if(transacciones==null) {
					transacciones = new LinkedList<Transaction>();
				}
				transacciones.add(transaction);
				buyer.setTransactions(transacciones);
				buyerRepository.save(buyer);
			}
			else {
				return "Error creating the transaction because de buyer doesn`t exist";
			}
		} catch (Exception ex) {
			return "Error creating the buyer: " + ex.toString();
		}
		return "Buyer succesfully created with id: " + buyerId;
	}

	@ResponseBody
	@GetMapping("/get/{id}/transaction")
	public List<Transaction> getTransactionsByBuyer(@PathVariable("id") int buyerId) {
		try {
			Buyer buyer = buyerRepository.findByBuyerId(buyerId);
			if (buyer == null) {
				throw new Exception("The Buyer with id: " + buyerId + " doesn´t exist in our database");
			}
			List<Transaction> transactions = buyer.getTransactions();
			return transactions;
		} catch (Exception ex) {
			return null;
		}
	}
	
	@DeleteMapping("/delete/{id}/transaction/{idTransaction}")
	@ResponseBody
	public String deleteTransactionByBuyer(@PathVariable("id") int buyerId, @PathVariable("idTransaction") int transactionId) {
		try {
			Transaction transaction = transactionRepository.findByTransactionId(transactionId);
			System.out.println("1");
			Buyer buyer = buyerRepository.findByBuyerId(buyerId);
			System.out.println("2");
			List<Transaction> lista = buyer.getTransactions();
			boolean encontro = false;
			for(int i = 0; i<lista.size()&&!encontro;i++) {
				if(lista.get(i).getTransactionId()==transactionId) {
					encontro=true;
				}
			}
			if(buyer != null && transaction != null && encontro) {
				System.out.println("3");
				lista.remove(transaction);
				
				System.out.println("4");
				buyer.setTransactions(lista);
				buyerRepository.save(buyer);
				transactionRepository.delete(transaction);
				System.out.println("5");
			}
			
			
		} catch (Exception ex) {
			return "Error creating the buyer: " + ex.toString();
		}
		return "Transaction succesfully deleted with id: " + transactionId;
	}
}
