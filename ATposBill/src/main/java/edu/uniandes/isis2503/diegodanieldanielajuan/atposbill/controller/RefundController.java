package edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.controller;

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

import edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.entity.Refund;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.repository.RefundRepository;

@Controller
@RequestMapping("/refunds")
public class RefundController {
	
	
	/**
	 * Connection to the Repository of Refund
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
	public Refund createRefund(@Valid @RequestBody Refund refund) {
		try {
			return refundRepository.save(refund);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * /refunds/update/{id} -> updated the refunds with passed
	 * 
	 * @return A Refund updated
	 */
	@PutMapping("/update/{id}")
	@ResponseBody
	public ResponseEntity<Refund> updateRefund(@PathVariable("id") long id, @Valid @RequestBody Refund refund) {
		try {
			Refund oldRefund = refundRepository.findRefundByID(id);
			if (oldRefund == null) {
				throw new Exception("The Refund with id: " + refund.getId() + " doesn´t exist in our database");
			}
			oldRefund.setDescription(refund.getDescription());
			final Refund updateRefund = refundRepository.save(oldRefund);
			return ResponseEntity.ok(updateRefund);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * /refunds/getbyid/{id} -> return the refund having the passed id.
	 * 
	 * @param id The id to search in the database.
	 * @return refund with the id
	 */
	@GetMapping("/getbyid/{id}")
	@ResponseBody
	public Refund getRefundID(@PathVariable("id") long id) {
		try {
			Refund refund = refundRepository.findRefundByID(id);
			if (refund == null) {
				throw new Exception("The Refund with id: " + id + " doesn´t exist in our database");
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
			List<Refund> refunds = refundRepository.getAllRefunds();
			return refunds;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /refunds/delete/{id} -> delete the refund having the passed id.
	 * 
	 * @param id The id for the refunds to delete
	 * @return A string describing if the refunds is succesfully deleted or not.
	 */
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteRefunds(@PathVariable("id") long id) {
		try {
			Refund refundDelete = refundRepository.findRefundByID(id);
			if (refundRepository.findRefundByID(id) == null) {
				throw new Exception("The Bill with id: " + id + " doesn´t exist in our database");
			}
			refundRepository.delete(refundDelete);
		} catch (Exception ex) {
			return "Error deleting the refund:" + ex.toString();
		}
		return "refund succesfully deleted with id : " + id;
	}
	
}
