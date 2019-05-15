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
import edu.uniandes.isis2503.diegodanieldanielajuan.atpostransaction.repository.BillRepository;


@Controller
@RequestMapping("/bills")
public class BillController {
	
	/**
	 * Connection to the Repository of bill
	 */
	@Autowired
	private BillRepository billRepository;
	

	/**
	 * /bills/create -> create a new bill and save it in the database.
	 * 
	 * @return a string describing if the bill is succesfully created or not.
	 */
	@PostMapping("/create")
	@ResponseBody
	public Bill createBill(@Valid @RequestBody Bill bill) {
		try {
			return billRepository.save(bill);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * /bills/update/{id} -> updated the bills with passed
	 * 
	 * @return A Bill updated
	 */
	@PutMapping("/update/{id}")
	@ResponseBody
	public ResponseEntity<Bill> updateBill(@PathVariable("id") long id, @Valid @RequestBody Bill bill) {
		try {
			Bill oldBill = billRepository.findBillByID(id);
			if (oldBill == null) {
				throw new Exception("The Bill with id: " + id + " doesn´t exist in our database");
			}
			oldBill.setValue(bill.getValue());
			final Bill updateBill = billRepository.save(oldBill);
			return ResponseEntity.ok(updateBill);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * /bills/getbyid/{id} -> return the bill having the passed id.
	 * 
	 * @param id The id to search in the database.
	 * @return Bill with the id
	 */
	@GetMapping("/getbyid/{id}")
	@ResponseBody
	public Bill getBillID(@PathVariable("id") long id) {
		try {
			Bill bill = billRepository.findBillByID(id);
			if (bill == null) {
				throw new Exception("The Bill with id: " + id + " doesn´t exist in our database");
			}
			return bill;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /bills/allbills -> return all the bills..
	 * 
	 * @return a empty list.
	 */
	@ResponseBody
	@GetMapping("/allbills")
	public List<Bill> getBills() {
		try {
			List<Bill> bills = billRepository.getAllBills();
			return bills;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /bills/delete/{id} -> delete the bill having the passed id.
	 * 
	 * @param id The id for the bills to delete
	 * @return A string describing if the bill is succesfully deleted or not.
	 */
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteBill(@PathVariable("id") long id) {
		try {
			Bill billDelete = billRepository.findBillByID(id);
			if (billRepository.findBillByID(id) == null) {
				throw new Exception("The Bill with id: " + id + " doesn´t exist in our database");
			}
			billRepository.delete(billDelete);
		} catch (Exception ex) {
			return "Error deleting the bill:" + ex.toString();
		}
		return "bill succesfully deleted with id : " + id;
	}
	
	
}
