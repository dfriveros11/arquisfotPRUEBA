package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.controller;

import java.util.LinkedList;
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

import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document.Product;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document.ProductPromotion;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.repository.ProductPromotionRepository;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.repository.ProductRepository;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	/**
	 * Connection to the repository of promotions
	 */
	private ProductRepository productRepository;

	/**
	 * Connection to the repository of productPromotion
	 */
	private ProductPromotionRepository productPromotionRepository;
	

	
	/**
	 * Constructor
	 */
	@Autowired
    public ProductController(final ProductRepository productRepository, final ProductPromotionRepository productPromotionRepository) {
        this.productRepository = productRepository;
        this.productPromotionRepository = productPromotionRepository;
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
			oldProduct.setProductPromotion(product.getProductPromotion());
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
	 * /products/allproducts -> return all the products..
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

	/**
	 * /products/delete/{id} -> delete the products having the passed id.
	 * 
	 * @param id The id of the product to delete
	 * @return A string describing if the products is succesfully deleted or not.
	 */
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteProduct(@PathVariable("id") int id) {
		try {
			Product product = productRepository.findByProductId(id);
			if (product == null) {
				throw new Exception("The Product with id: " + id + " doesn´t exist in our database");
			}
			productRepository.delete(product);
		} catch (Exception ex) {
			return "Error deleting the Product:" + ex.toString();
		}
		return "Product succesfully deleted with id : " + id;
	}

	//Relation with ProductPromotion
	
	@PostMapping("create/{id}/productpromotions")
	@ResponseBody
	public String createProductPromotion(@PathVariable("id") int id, @RequestBody @Valid ProductPromotion productPromotion) {
		try {
			Product product = productRepository.findByProductId(id);
			if (product == null) {
				throw new Exception("The Product  with id:  " + id + " doesn't exist");
			}
			ProductPromotion productPromotionTest = product.getProductPromotion();
			if (productPromotionTest != null) {
				throw new Exception("The Product with id: " + id + " has already a promotion");
			}
			productRepository.save(product);
			List<Product> products = new LinkedList<Product>();
			products.add(product);
			productPromotion.setProducts(products);
			productPromotionRepository.save(productPromotion);
		} catch (Exception ex) {
			return "Error creating ProductPromotion related with the Product with id: " + id;
		}
		return "ProductPromotion  with id " + productPromotion.getProductPromotionId() + " succesfully created linked to Product  with id: " + id;
		
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/getbyid/{id}/productpromotions/{idP}")
	@ResponseBody
	public ProductPromotion findProductProductPromotion(@PathVariable("id") int id, @PathVariable("idP") int idP) {
		try {
			Product product =  productRepository.findByProductId(id);
			if (product == null) {
				throw new Exception("The Product  with id:  " + id + " doesn't exist");
			}
			ProductPromotion productPromotion = productPromotionRepository.findPromotion(idP, id);
			return productPromotion;
		} catch (Exception e) {
			return null;
		}

	}

	@PutMapping("/update/{id}/productpromotions/{idP}")
	@ResponseBody
	public ProductPromotion updateProductPromotion(@PathVariable("id") int id, @PathVariable("idP") int idP) {
		try {
			Product product = productRepository.findByProductId(id);
			if (product == null) {
				throw new Exception("The Product with id:  " + id + " doesn't exist");
			}
			ProductPromotion newProductPromotion = productPromotionRepository.findByProductPromotionId(idP);
			if (newProductPromotion == null) {
				throw new Exception("The ProductPromotion  with id:  " + idP + " doesn't exist");
			}
			List<Product> products = new LinkedList<Product>();
			products.add(product);
			newProductPromotion.setProducts(products);
			productPromotionRepository.save(newProductPromotion);
			return newProductPromotion;
		} catch (Exception e) {
			return null;
		}
	}

	@DeleteMapping("/delete/{id}/productpromotions/{idP}")
	@ResponseBody
	public String deleteProductProductPromotions(@PathVariable("id") int id, @PathVariable("idP") int idP) {
		try {
			Product product = productRepository.findByProductId(id);
			if (product == null) {
				throw new Exception("The Product  with id:  " + id + " doesn't exist");
			}
			ProductPromotion productPromotion = productPromotionRepository.findByProductPromotionId(idP);
			if (productPromotion == null) {
				throw new Exception("The ProductPromotion  with id:  " + idP + " doesn't exist");
			}		
			productPromotionRepository.delete(productPromotion);
		} catch (Exception e) {
			return "Error deleting the ProductPromotion :" + e.toString();
		}
		return "ProductPromotion  with id " + idP + " has been succesfully deleted!";
	}
	
}
