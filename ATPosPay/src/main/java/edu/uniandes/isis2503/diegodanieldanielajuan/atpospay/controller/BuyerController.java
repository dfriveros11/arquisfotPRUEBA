package edu.uniandes.isis2503.diegodanieldanielajuan.atpospay.controller;

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

import edu.uniandes.isis2503.diegodanieldanielajuan.atpospay.entity.Buyer;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpospay.repository.BuyerRepository;

@Controller
@RequestMapping("/buyers")
public class BuyerController {
	
	
	/**
	 * Connection to the Repository of buyer
	 */
	@Autowired
	private BuyerRepository buyerRepository;

	/**
	 * /buyers/create -> create a new buyer and save it in the database.
	 * 
	 * @return a string describing if the buyer is succesfully created or not.
	 */
	@PostMapping("/create")
	@ResponseBody
	public Buyer createbuyer(@Valid @RequestBody Buyer buyer) {
		try {
			return buyerRepository.save(buyer);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * /buyers/update/{id} -> updated the buyers with passed
	 * 
	 * @return A buyer updated
	 */
	@PutMapping("/update/{id}")
	@ResponseBody
	public ResponseEntity<Buyer> updatebuyer(@PathVariable("id") long id, @Valid @RequestBody Buyer buyer) {
		try {
			Buyer oldbuyer = buyerRepository.findBuyerByID(id);
			if (oldbuyer == null) {
				throw new Exception("The buyer with id: " + buyer.getId() + " doesn´t exist in our database");
			}
			oldbuyer.setName(buyer.getName());
			oldbuyer.setEmail(buyer.getEmail());
			final Buyer updatebuyer = buyerRepository.save(oldbuyer);
			return ResponseEntity.ok(updatebuyer);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * /buyers/getbyid/{id} -> return the buyer having the passed id.
	 * 
	 * @param id The id to search in the database.
	 * @return buyer with the id
	 */
	@GetMapping("/getbyid/{id}")
	@ResponseBody
	public Buyer getbuyerID(@PathVariable("id") long id) {
		try {
			Buyer buyer = buyerRepository.findBuyerByID(id);
			if (buyer == null) {
				throw new Exception("The buyer with id: " + id + " doesn´t exist in our database");
			}
			return buyer;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /buyers/allbuyers -> return all the buyers..
	 * 
	 * @return a empty list.
	 */
	@ResponseBody
	@GetMapping("/allbuyers")
	public List<Buyer> getbuyers() {
		try {
			List<Buyer> buyers = buyerRepository.getAllBuyers();
			return buyers;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /buyers/delete/{id} -> delete the buyer having the passed id.
	 * 
	 * @param id The id for the buyers to delete
	 * @return A string describing if the buyers is succesfully deleted or not.
	 */
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deletebuyers(@PathVariable("id") long id) {
		try {
			Buyer buyerDelete = buyerRepository.findBuyerByID(id);
			if (buyerRepository.findBuyerByID(id) == null) {
				throw new Exception("The Bill with id: " + id + " doesn´t exist in our database");
			}
			buyerRepository.delete(buyerDelete);
		} catch (Exception ex) {
			return "Error deleting the buyer:" + ex.toString();
		}
		return "buyer succesfully deleted with id : " + id;
	}
	
}
