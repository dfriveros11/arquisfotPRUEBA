package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.controller;

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

import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document.ProductPromotion;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.repository.ProductPromotionRepository;


@Controller
@RequestMapping("/productpromotions")
public class ProductPromotionController {
	

	/**
	 * Connection to the repository of productPromotion
	 */
	@Autowired
	private ProductPromotionRepository ProductPromotionRepository;

	/**
	 * /productpromotions/create -> create a new productpromotions and save it in the database.
	 * 
	 * @return a string describing if the ProductPromotion is succesfully created or not.
	 */
	@PostMapping("/create")
	@ResponseBody
	public String createProductPromotion(@RequestBody @Valid ProductPromotion productPromotion) {
		try {
			ProductPromotion productPromotionTest = ProductPromotionRepository.findByProductPromotionId(productPromotion.getProductPromotionId());
			if(productPromotionTest != null) {
				throw new DuplicateKeyException("The ProductPromotion's id exist");
			}
			ProductPromotionRepository.insert(productPromotion);
		} catch (Exception ex) {
			return "Error creating the product: " + ex.toString();
		}
		return "ProductPromotion succesfully created with id: " + productPromotion.getProductPromotionId();
	}

	/**
	 * /productpromotions/update -> updated the ProductPromotion with passed
	 * 
	 * @return A ProductPromotion updated
	 */
	@PutMapping("/update")
	@ResponseBody
	public ProductPromotion updateProductPromotion(@RequestBody @Valid ProductPromotion productPromotion) {
		try {

			ProductPromotion oldProductPromotion = ProductPromotionRepository.findByProductPromotionId(productPromotion.getProductPromotionId());
			if (oldProductPromotion == null) {
				throw new Exception("The Product with id: " + productPromotion.getProductPromotionId() + " doesn´t exist in our database");
			}
			oldProductPromotion.setPromotion(productPromotion.getPromotion());
			ProductPromotionRepository.save(oldProductPromotion);
			return oldProductPromotion;
		} catch (Exception ex) {
			ex.toString();
			return null;
		}
	}

	/**
	 * /products/getbyid/{id} -> return the ProductPromotion having the passed id.
	 * 
	 * @param id The id to search in the database.
	 * @return ProductPromotion with the id
	 */
	@GetMapping("/getbyid/{id}")
	@ResponseBody
	public ProductPromotion getProductPromotionID(@PathVariable("id") int id) {
		try {
			ProductPromotion productPromotion = ProductPromotionRepository.findByProductPromotionId(id);
			if (productPromotion == null) {
				throw new Exception("The Product with id: " + id + " doesn´t exist in our database");
			}
			return productPromotion;
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
	@GetMapping("/allproducts")
	public List<ProductPromotion> getProductPromotions() {
		try {
			List<ProductPromotion> productPromotions = ProductPromotionRepository.findAll();
			return productPromotions;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /products/delete/{id} -> delete the ProductPromotion having the passed id.
	 * 
	 * @param id The id for the bills to delete
	 * @return A string describing if the ProductPromotion is succesfully deleted or not.
	 */
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteProductPromotion(@PathVariable("id") int id) {
		try {
			ProductPromotion product = ProductPromotionRepository.findByProductPromotionId(id);
			if (product == null) {
				throw new Exception("The Product with id: " + id + " doesn´t exist in our database");
			}
			ProductPromotionRepository.delete(product);
		} catch (Exception ex) {
			return "Error deleting the Product:" + ex.toString();
		}
		return "Product succesfully deleted with id : " + id;
	}

}
