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

import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document.Refund;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.repository.RefundRepository;

@Controller
@RequestMapping("/refunds")
public class RefundController {
	/**
	 * Connection with DAO of refund
	 */
	@Autowired
	private RefundRepository refundRepository;

	/**
	 * /refunds/create -> create a new refund and save it in the database.
	 * 
	 * @return a string describing if the refund is succesfully created or not.
	 */
	@PostMapping("/create")
	@ResponseBody
	public String createRefund(@RequestBody @Valid Refund refund) {
		try {
			Refund ref = refundRepository.findByRefundId(refund.getRefundId());
			if ( ref == null) {
				refundRepository.save(refund);
			}
			else {
				return "Error creating the refund: The refund with id "+ refund.getRefundId()+ " already exists.";
			}
			refundRepository.save(refund);
		} catch (Exception ex) {
			return "Error creating the refund: " + ex.toString();
		}
		return "Refund succesfully created with id: " + refund.getId();
	}

	/**
	 * /refunds/update -> updated the refund with passed
	 * 
	 * @return the refund updated
	 */
	@PutMapping("/update")
	@ResponseBody
	public Refund updateRefund(@RequestBody @Valid Refund refund) {
		try {

			Refund oldRefund = refundRepository.findByRefundId(refund.getRefundId());
			if (oldRefund == null) {
				throw new Exception("The refund with id: " + refund.getId() + " doesn´t exist in our database");
			}
			oldRefund.setDescription(refund.getDescription());
			oldRefund.setCreationDate(refund.getCreationDate());
			refundRepository.save(oldRefund);
			return oldRefund;
		} catch (Exception ex) {
			ex.toString();
			return null;
		}
	}

	/**
	 * /refund/getbyid/{id} -> return the refund having the passed id.
	 * 
	 * @param id The id to search in the database.
	 * @return The refund or a message error if the refund is not found.
	 */
	@GetMapping("/getbyid/{id}")
	@ResponseBody
	public Refund getRefundById(@PathVariable("id") int id) {
		try {
			Refund refund = refundRepository.findByRefundId(id);
			if (refund == null) {
				throw new Exception("The refund with id: " + id + " doesn´t exist in our database");
			}
			return refund;
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
	@GetMapping("/allrefunds")
	public List<Refund> getRefunds() {
		try {
			List<Refund> refunds = refundRepository.findAll();
			return refunds;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /refunds/delete/{id} -> delete the refund having the passed id.
	 * 
	 * @param id The id for the booking to delete
	 * @return A string describing if the refund is succesfully deleted or not.
	 */
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteRefund(@PathVariable("id") int id) {
		try {
			Refund toDelete = refundRepository.findByRefundId(id); 
			if ( toDelete == null) {
				throw new Exception("The refund with id: " + id + " doesn´t exist in our database");
			}
			refundRepository.delete(toDelete);
		} catch (Exception ex) {
			return "Error deleting the refund:" + ex.toString();
		}
		return "refund succesfully deleted with id: " + id;
	}

}
