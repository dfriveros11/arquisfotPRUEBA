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

import edu.uniandes.isis2503.diegodanieldanielajuan.atpostransaction.entity.Pay;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpostransaction.repository.PayRepository;

@Controller
@RequestMapping("/pays")
public class PayController {
	
	/**
	 * Connection to the Repository of transaction
	 */
	@Autowired
	private PayRepository payRepository;
	

	/**
	 * /pays/create -> create a new pay and save it in the database.
	 * 
	 * @return a string describing if the pay is succesfully created or not.
	 */
	@PostMapping("/create")
	@ResponseBody
	public Pay createTransaction(@Valid @RequestBody Pay pay) {
		try {
			return payRepository.save(pay);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * /pays/update/{id} -> updated the pay with passed
	 * 
	 * @return A pay updated
	 */
	@PutMapping("/update/{id}")
	@ResponseBody
	public ResponseEntity<Pay> updateTransaction(@PathVariable("id") long id, @Valid @RequestBody Pay pay) {
		try {
			Pay oldPay = payRepository.findPayByID(id);
			if (oldPay == null) {
				throw new Exception("The Pay with id: " + id + " doesn´t exist in our database");
			}
			oldPay.setName(pay.getName());
			oldPay.setDescription(pay.getDescription());
			final Pay updatePay = payRepository.save(oldPay);
			return ResponseEntity.ok(updatePay);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * /pays/getbyid/{id} -> return the pay having the passed id.
	 * 
	 * @param id The id to search in the database.
	 * @return pay with the id
	 */
	@GetMapping("/getbyid/{id}")
	@ResponseBody
	public Pay getTransactionID(@PathVariable("id") long id) {
		try {
			Pay pay = payRepository.findPayByID(id);
			if (pay == null) {
				throw new Exception("The Pay with id: " + id + " doesn´t exist in our database");
			}
			return pay;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /bills/allpays-> return all the transactions..
	 * 
	 * @return a empty list.
	 */
	@ResponseBody
	@GetMapping("/allpays")
	public List<Pay> getTransactions() {
		try {
			List<Pay> pay = payRepository.getAllPays();
			return pay;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /pays/delete/{id} -> delete the pay having the passed id.
	 * 
	 * @param id The id for the pays to delete
	 * @return A string describing if the pay is succesfully deleted or not.
	 */
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteTransaction(@PathVariable("id") long id) {
		try {
			Pay payDelete = payRepository.findPayByID(id);
			if (payRepository.findPayByID(id) == null) {
				throw new Exception("The Bill with id: " + id + " doesn´t exist in our database");
			}
			payRepository.delete(payDelete);
		} catch (Exception ex) {
			return "Error deleting the pay:" + ex.toString();
		}
		return "Pay succesfully deleted with id : " + id;
	}

}
