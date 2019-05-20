package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.atposproduct.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.atposproduct.document.Product;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.atposproduct.repository.ProductRepository;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	/**
	 * Connection to the repository of promotions
	 */
	private ProductRepository productRepository;

	
	/**
	 * Constructor
	 */
	@Autowired
    public ProductController(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

	
	/**
	 * /products/create -> create a new product and save it in the database.
	 * 
	 * @return a string describing if the product is succesfully created or not.
	 */
	@PostMapping("/create")
	@ResponseBody
	public String createProduct(@RequestBody @Valid Product product) {
		try {
			Product productTest = productRepository.findByProductId(product.getProductId());
			if(productTest != null) {
				throw new DuplicateKeyException("The Product's id exist");
			}
			productRepository.insert(product);
		} catch (Exception ex) {
			return "Error creating the product: " + ex.toString();
		}
		return "Product succesfully created with id: " + product.getProductId();
	}

	/**
	 * /products/update -> updated the Product with passed
	 * 
	 * @return A Product updated
	 */
	@PutMapping("/update")
	@ResponseBody
	public Product updateProduct(@RequestBody @Valid Product product) {
		try {

			Product oldProduct = productRepository.findByProductId(product.getProductId());
			if (oldProduct == null) {
				throw new Exception("The Product with id: " + product.getProductId() + " doesn´t exist in our database");
			}
			oldProduct.setPrice(product.getPrice());
			productRepository.save(oldProduct);
			return oldProduct;
		} catch (Exception ex) {
			ex.toString();
			return null;
		}
	}

	/**
	 * /products/getbyid/{id} -> return the products having the passed id.
	 * 
	 * @param id The id to search in the database.
	 * @return Product with the id
	 */
	@GetMapping("/getbyid/{id}")
	@ResponseBody
	public Product getProductID(@PathVariable("id") int id) {
		try {
			Product product = productRepository.findByProductId(id);
			if (product == null) {
				throw new Exception("The Product with id: " + id + " doesn´t exist in our database");
			}
			return product;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /product/allproducts -> return all the products..
	 * 
	 * @return a empty list.
	 */
	@GetMapping("/allproducts")
	@ResponseBody
	public List<Product> getProducts() {
		try {
			List<Product> products = productRepository.findAll();
			return products;
		} catch (Exception ex) {
			return null;
		}
	}
}
