package edu.uniandes.isis2503.diegodanieldanielajuan.atpospromotion.controller;

import java.util.ArrayList;
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

import edu.uniandes.isis2503.diegodanieldanielajuan.atpospromotion.entity.Promotion;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpospromotion.repository.PromotionRepository;

@Controller
@RequestMapping("/{applicationName}")
public class PromotionController {
	
	/**
	 * Connection to the repository of Promotion
	 */
	@Autowired
	private PromotionRepository promotionRepository;

	/**
	 * /promotions/create -> create a new promotion and save it in the
	 * database.
	 * 
	 * @return a string describing if the Promotion is succesfully created or
	 *         not.
	 */
	@PostMapping("/create")
	@ResponseBody
	public String createPromotion(@RequestBody @Valid Promotion promotion) {
		try {
			Promotion promotionNew = new Promotion(promotion.getProductId(), promotion.getPromotionId(),
					promotion.getValue());
			promotionRepository.save(promotionNew);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "Promotion succesfully created with id: " + promotion.getPromotionId();
	}

	/**
	 * /promotions/update -> updated the ProductPromotion with passed
	 * 
	 * @return A ProductPromotion updated
	 */
	@PutMapping("/update/{id}")
	@ResponseBody
	public Promotion updatePromotion(@PathVariable("id") String id, @RequestBody @Valid Promotion promotion) {
		try {

			Promotion oldPromotion = promotionRepository.findById(id).get();
			if (oldPromotion == null) {
				throw new Exception("The Product with id: " + promotion.getProductId() + " doesn´t have a promotion");
			}
			oldPromotion.setValue(promotion.getValue());
			promotionRepository.save(oldPromotion);
			return oldPromotion;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /promotions/getbyid/{id} -> return the Promotion having the passed id.
	 * 
	 * @param id The id to search in the database.
	 * @return ProductPromotion with the id
	 */
	@GetMapping("/getbyid/{id}")
	@ResponseBody
	public Promotion getPromotionID(@PathVariable("id") String id) {
		try {
			Promotion promotion = promotionRepository.findById(id).get();
			if (promotion == null) {
				throw new Exception("The Product with id: " + id + " doesn´t have a promotion");
			}
			return promotion;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * /promotions/allpromotions -> return all the promotions..
	 * 
	 * @return a empty list.
	 */
	@GetMapping("/allpromotions")
	@ResponseBody
	public List<Promotion> allPromotions() {
		try {
			List<Promotion> promotions = new ArrayList<Promotion>();
			promotionRepository.findAll().forEach(promotions::add);
			return promotions;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /promotions/delete/{id} -> delete the promotions having the passed id.
	 * 
	 * @param id The id for the bills to delete
	 * @return A string describing if the ProductPromotion is succesfully deleted or
	 *         not.
	 */
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteProductPromotion(@PathVariable("id") String id) {
		try {
			Promotion promotion = promotionRepository.findById(id).get();
			if (promotion == null) {
				throw new Exception("The Product with id: " + id + " doesn´t have a promotion");
			}
			promotionRepository.delete(promotion);
		} catch (Exception ex) {
			return "Error deleting the Promotion:" + ex.toString();
		}
		return "Promotion succesfully deleted with id : " + id;
	}

}
