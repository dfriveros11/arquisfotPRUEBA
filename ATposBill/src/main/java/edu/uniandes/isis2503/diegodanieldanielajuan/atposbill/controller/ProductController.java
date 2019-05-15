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

import edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.entity.Product;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.entity.Promotion;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.repository.ProductRepository;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.repository.PromotionRepository;

@Controller
@RequestMapping("/products")
public class ProductController {

	
	/**
	 * Connection to the Repository of Product
	 */
	@Autowired
	private ProductRepository productRepository;
	
	
	/**
	 * Connection to the Repository of Promotion
	 */
	@Autowired
	private PromotionRepository promotionRepository;
	

	/**
	 * /products/create -> create a new Product and save it in the database.
	 * 
	 * @return a string describing if the Product is succesfully created or not.
	 */
	@PostMapping("/create")
	@ResponseBody
	public Product createProduct(@Valid @RequestBody Product product) {
		try {
			return productRepository.save(product);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * /products/update/{id} -> updated the products with passed
	 * 
	 * @return A Product updated
	 */
	@PutMapping("/update/{id}")
	@ResponseBody
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @Valid @RequestBody Product product) {
		try {
			Product oldProduct = productRepository.findProductByID(id);
			if (oldProduct == null) {
				throw new Exception("The Product with id: " + id + " doesn´t exist in our database");
			}
			oldProduct.setName(product.getName());
			oldProduct.setPrice(product.getPrice());
			final Product updateProduct = productRepository.save(oldProduct);
			return ResponseEntity.ok(updateProduct);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * /products/getbyid/{id} -> return the product having the passed id.
	 * 
	 * @param id The id to search in the database.
	 * @return product with the id
	 */
	@GetMapping("/getbyid/{id}")
	@ResponseBody
	public Product getProductID(@PathVariable("id") long id) {
		try {
			Product product = productRepository.findProductByID(id);
			if (product == null) {
				throw new Exception("The Product with id: " + id + " doesn´t exist in our database");
			}
			return product;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /products/allproducts -> return all the products..
	 * 
	 * @return a empty list.
	 */
	@ResponseBody
	@GetMapping("/allproducts")
	public List<Product> getProducts() {
		try {
			List<Product> product = productRepository.getAllProducts();
			return product;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /products/delete/{id} -> delete the product having the passed id.
	 * 
	 * @param id The id for the products to delete
	 * @return A string describing if the products is succesfully deleted or not.
	 */
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteProduct(@PathVariable("id") long id) {
		try {
			Product productDelete = productRepository.findProductByID(id);
			if (productRepository.findProductByID(id) == null) {
				throw new Exception("The Product with id: " + id + " doesn´t exist in our database");
			}
			productRepository.delete(productDelete);
		} catch (Exception ex) {
			return "Error deleting the Product:" + ex.toString();
		}
		return "Product succesfully deleted with id : " + id;
	}
	
	
	/* CONNECTION WITH PROMOTION */
	
	@PostMapping("create/{id}/promotions")
	@ResponseBody
	public Promotion createProductPromotion(@PathVariable("id") long id, @RequestBody @Valid Promotion promotion) {
		try {
			Product product = productRepository.findProductByID(id);
			if (product == null) {
				throw new Exception("The Product  with id:  " + id + " doesn't exist");
			}
			Promotion promotionTest = product.getPromotion();
			if (promotionTest != null) {
				throw new Exception("The Product with id: " + id + " has already a promotion");
			}
			promotion.setProduct(product);
			return promotionRepository.save(promotion);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/getbyid/{id}/promotions/{idP}")
	@ResponseBody
	public Promotion getProductPromotion(@PathVariable("id") long id, @PathVariable("idP") int idP) {
		try {
			Product product =  productRepository.findProductByID(id);
			if (product == null) {
				throw new Exception("The Product  with id:  " + id + " doesn't exist");
			}
			Promotion promotion = product.getPromotion();
			if(promotion == null) {
				throw new Exception("Promotion doesn't exist");
			}
			if (Long.compare(promotion.getId(), idP) != 0) {
				throw new Exception("Promotion with that id:" + idP + "doesn't exist");
			}
			return promotion;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
}
