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

import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document.Bill;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document.Refund;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.repository.BillRepository;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.repository.RefundRepository;

@Controller
@RequestMapping("/bills")
public class BillController {
	/**
	 * Connection with DAO of bill
	 */
	@Autowired
	private BillRepository billRepository;
	
	/**
	 * Connection with DAO of Refund
	 */
	@Autowired
	private RefundRepository refundRepository;
	
	/**
	 * /refunds/create -> create a new bill and save it in the database.
	 * 
	 * @return a string describing if the bill is succesfully created or not.
	 */
	@PostMapping("/create")
	@ResponseBody
	public String createBill(@RequestBody @Valid Bill bill) {
		try {
			Bill ref = billRepository.findByBillId(bill.getBillId());
			if ( ref == null) {
				billRepository.save(bill);
			}
			else {
				return "Error creating the bill: The bill with id "+ bill.getBillId()+ " already exists.";
			}
			billRepository.save(bill);
		} catch (Exception ex) {
			return "Error creating the bill: " + ex.toString();
		}
		return "Bill succesfully created with id: " + bill.getId();
	}

	/**
	 * /refunds/update -> updated the bill with passed
	 * 
	 * @return the bill updated
	 */
	@PutMapping("/update")
	@ResponseBody
	public Bill updateBill(@RequestBody @Valid Bill bill) {
		try {

			Bill oldBill = billRepository.findByBillId(bill.getBillId());
			if (oldBill == null) {
				throw new Exception("The bill with id: " + bill.getId() + " doesn´t exist in our database");
			}
			oldBill.setValue(bill.getValue());
			billRepository.save(oldBill);
			return oldBill;
		} catch (Exception ex) {
			ex.toString();
			return null;
		}
	}

	/**
	 * /bill/getbyid/{id} -> return the bill having the passed id.
	 * 
	 * @param id The id to search in the database.
	 * @return The bill or a message error if the bill is not found.
	 */
	@GetMapping("/getbyid/{id}")
	@ResponseBody
	public Bill getBillById(@PathVariable("id") int id) {
		try {
			Bill bill = billRepository.findByBillId(id);
			if (bill == null) {
				throw new Exception("The bill with id: " + id + " doesn´t exist in our database");
			}
			return bill;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /refunds/allrefunds -> return all the refunds..
	 * 
	 * @return a empty list.
	 */
	@ResponseBody
	@GetMapping("/allbills")
	public List<Bill> getBills() {
		try {
			List<Bill> refunds = billRepository.findAll();
			return refunds;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /refunds/delete/{id} -> delete the bill having the passed id.
	 * 
	 * @param id The id for the booking to delete
	 * @return A string describing if the bill is succesfully deleted or not.
	 */
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteBill(@PathVariable("id") int id) {
		try {
			Bill toDelete = billRepository.findByBillId(id); 
			if ( toDelete == null) {
				throw new Exception("The bill with id: " + id + " doesn´t exist in our database");
			}
			billRepository.delete(toDelete);
		} catch (Exception ex) {
			return "Error deleting the bill:" + ex.toString();
		}
		return "bill succesfully deleted with id: " + id;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}/refund")
	@ResponseBody
	public Refund findRefundByBill(@PathVariable("id") int id) {
		try {
			Bill bill = billRepository.findByBillId(id);
			if (bill == null) {
				throw new Exception("The bill with id:  " + id + " doesn't exist");
			}
			return bill.getRefund();
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Create a refund by a bill
	 * @param id Id if the bill
	 * @param refund. Refund to associate
	 * @return message
	 */
	@PostMapping("/{id}/create/refund")
	@ResponseBody
	public String createRefundByBill(@PathVariable("id") int id, @RequestBody @Valid Refund refund) {
		try {
			Bill bill = billRepository.findByBillId(id);
			if (bill == null) {
				throw new Exception("The bill with id:  " + id + " doesn't exist");
			}
			Refund ref = refundRepository.findByRefundId(refund.getRefundId());
			if( ref == null) {
				throw new Exception("The refund " +refund.getRefundId()+" does not exist"); 
			}
			bill.setRefund(ref);
			billRepository.save(bill);
			return "The refund " + ref.getRefundId() + " has been associated with the bill " + id;
		} catch (Exception e) {
			return "The refund "+ refund.getRefundId() + " can't be created in the bill "+ id;
		}

	}
	
	/**
	 * /refunds/delete/{id} -> delete the bill having the passed id.
	 * 
	 * @param id The id for the booking to delete
	 * @return A string describing if the bill is succesfully deleted or not.
	 */
	@DeleteMapping("/{id}/delete/refund")
	@ResponseBody
	public String deleteRefund(@PathVariable("id") int id) {
		try {
			Bill toDelete = billRepository.findByBillId(id); 
			if ( toDelete == null) {
				throw new Exception("The bill with id: " + id + " doesn´t exist in our database");
			}
			toDelete.setRefund(null);
			billRepository.save(toDelete);
		} catch (Exception ex) {
			return "Error deleting the bill:" + ex.toString();
		}
		return "refund succesfully deleted with id: " + id;
	}
	
	/**
	 * /refunds/update -> updated the bill with passed
	 * 
	 * @return the bill updated
	 */
	@PutMapping("/{id}/update/refund")
	@ResponseBody
	public Refund updateRefund(@RequestBody @Valid Refund refund, @PathVariable("id") int id) {
		try {

			Bill bill = billRepository.findByBillId(id);
			if (bill == null) {
				throw new Exception("The bill with id: " + id + " doesn´t exist in our database");
			}
			Refund ref = bill.getRefund();
			ref.setRefundId(refund.getRefundId());
			ref.setCreationDate(refund.getCreationDate());
			ref.setDescription(refund.getDescription());
			ref.setValue(refund.getValue());
			bill.setRefund(ref);
			billRepository.save(bill);
			return ref;
		} catch (Exception ex) {
			ex.toString();
			return null;
		}
	}
	
}
