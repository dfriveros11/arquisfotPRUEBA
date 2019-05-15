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

import edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.entity.Promotion;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.repository.PromotionRepository;

@Controller
@RequestMapping("/promotions")
public class PromotionController {
	

	/**
	 * Connection to the Repository of Promotion
	 */
	@Autowired
	private PromotionRepository promotionRepository;

	/**
	 * /promotions/create -> create a new promotion and save it in the database.
	 * 
	 * @return a string describing if the promotion is succesfully created or not.
	 */
	@PostMapping("/create")
	@ResponseBody
	public Promotion createPromotion(@Valid @RequestBody Promotion promotion) {
		try {
			return promotionRepository.save(promotion);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * /promotions/update/{id} -> updated the promotion with passed id
	 * 
	 * @return A promotion updated
	 */
	@PutMapping("/update/{id}")
	@ResponseBody
	public ResponseEntity<Promotion> updatePromotion(@PathVariable("id") long id, @Valid @RequestBody Promotion promotion) {
		try {
			Promotion oldPromotion = promotionRepository.findPromotionByID(id);
			if (oldPromotion == null) {
				throw new Exception("The Product with id: " + id + " doesn´t exist in our database");
			}
			oldPromotion.setValue(promotion.getValue());
			final Promotion updatePromotion = promotionRepository.save(oldPromotion);
			return ResponseEntity.ok(updatePromotion);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * /promotions/getbyid/{id} -> return the promotion having the passed id.
	 * 
	 * @param id The id to search in the database.
	 * @return promotion with the id
	 */
	@GetMapping("/getbyid/{id}")
	@ResponseBody
	public Promotion getPromotionID(@PathVariable("id") long id) {
		try {
			Promotion promotion = promotionRepository.findPromotionByID(id);
			if (promotion == null) {
				throw new Exception("The Product with id: " + id + " doesn´t exist in our database");
			}
			return promotion;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /promotion/allpromotions -> return all the promotions..
	 * 
	 * @return a empty list.
	 */
	@ResponseBody
	@GetMapping("/allpromotions")
	public List<Promotion> getPromotions() {
		try {
			List<Promotion> promotion = promotionRepository.getAllPromotions();
			return promotion;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /promotions/delete/{id} -> delete the promotion having the passed id.
	 * 
	 * @param id The id for the promotions to delete
	 * @return A string describing if the promotions is succesfully deleted or not.
	 */
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deletePromotion(@PathVariable("id") long id) {
		try {
			Promotion promotionDelete = promotionRepository.findPromotionByID(id);
			if (promotionRepository.findPromotionByID(id) == null) {
				throw new Exception("The Promotion with id: " + id + " doesn´t exist in our database");
			}
			promotionRepository.delete(promotionDelete);
		} catch (Exception ex) {
			return "Error deleting the Promotion:" + ex.toString();
		}
		return "Promotion succesfully deleted with id : " + id;
	}
	
}
