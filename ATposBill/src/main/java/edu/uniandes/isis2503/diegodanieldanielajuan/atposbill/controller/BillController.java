package edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.controller;

import java.util.Date;
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

import edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.entity.Bill;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.entity.Product;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.entity.Refund;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.repository.BillRepository;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.repository.ProductRepository;
import edu.uniandes.isis2503.diegodanieldanielajuan.atposbill.repository.RefundRepository;

@Controller
@RequestMapping("/{applicationName}")
public class BillController {

	/**
	 * Connection to the Repository of bill
	 */
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private RefundRepository refundRepository;
	
	@Autowired
	private ProductRepository productRepository;

	/**
	 * /bills/create -> create a new bill and save it in the database.
	 * 
	 * @return a string describing if the bill is succesfully created or not.
	 */
	@PostMapping("/create")
	@ResponseBody
	public Bill createBill(@Valid @RequestBody Bill bill) {
		try {
			return billRepository.save(bill);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * /bills/update/{id} -> updated the bills with passed
	 * 
	 * @return A Bill updated
	 */
	@PutMapping("/update/{id}")
	@ResponseBody
	public ResponseEntity<Bill> updateBill(@PathVariable("id") long id, @Valid @RequestBody Bill bill) {
		try {
			Bill oldBill = billRepository.findBillByID(id);
			if (oldBill == null) {
				throw new Exception("The Bill with id: " + id + " doesn´t exist in our database");
			}
			oldBill.setValue(bill.getValue());
			oldBill.setLastModifiedDate(new Date());
			final Bill updateBill = billRepository.save(oldBill);
			return ResponseEntity.ok(updateBill);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * /bills/getbyid/{id} -> return the bill having the passed id.
	 * 
	 * @param id The id to search in the database.
	 * @return Bill with the id
	 */
	@GetMapping("/getbyid/{id}")
	@ResponseBody
	public Bill getBillID(@PathVariable("id") long id) {
		try {
			Bill bill = billRepository.findBillByID(id);
			if (bill == null) {
				throw new Exception("The Bill with id: " + id + " doesn´t exist in our database");
			}
			return bill;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /bills/allbills -> return all the bills..
	 * 
	 * @return a empty list.
	 */
	@ResponseBody
	@GetMapping("/allbills")
	public List<Bill> getBills() {
		try {
			List<Bill> bills = billRepository.getAllBills();
			return bills;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /bills/delete/{id} -> delete the bill having the passed id.
	 * 
	 * @param id The id for the bills to delete
	 * @return A string describing if the bill is succesfully deleted or not.
	 */
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteBill(@PathVariable("id") long id) {
		try {
			Bill billDelete = billRepository.findBillByID(id);
			if (billRepository.findBillByID(id) == null) {
				throw new Exception("The Bill with id: " + id + " doesn´t exist in our database");
			}
			billRepository.delete(billDelete);
		} catch (Exception ex) {
			return "Error deleting the bill:" + ex.toString();
		}
		return "bill succesfully deleted with id : " + id;
	}
	
	/* CONNECTION WITH REFUND */
	
	@PostMapping("create/{id}/refunds")
	@ResponseBody
	public Refund createBillRefund(@PathVariable("id") long id, @RequestBody @Valid Refund refund) {
		try {
			Bill bill = billRepository.findBillByID(id);
			if (bill == null) {
				throw new Exception("The Bill with id:  " + id + " doesn't exist");
			}
			Refund refundTest = bill.getRefund();
			if (refundTest != null) {
				throw new Exception("The Bill with id: " + id + " has already a refund");
			}
			refund.setBill(bill);
			return refundRepository.save(refund);
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
	@GetMapping("/getbyid/{id}/refunds/{idR}")
	@ResponseBody
	public Refund getBillRefund(@PathVariable("id") long id, @PathVariable("idR") int idR) {
		try {
			Bill bill = billRepository.findBillByID(id);
			if (bill == null) {
				throw new Exception("The Bill  with id:  " + id + " doesn't exist");
			}
			Refund refund = bill.getRefund();
			if(refund == null) {
				throw new Exception("Refund doesn't exist");
			}
			if (Long.compare(refund.getId(), idR) != 0) {
				throw new Exception("Refund with that id:" + idR + "doesn't exist");
			}
			return refund;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/* CONNECTION WITH PRODUCT */
	
	@PostMapping("create/{id}/products/{idP}")
	@ResponseBody
	public Product createBillProduct(@PathVariable("id") long id, @PathVariable("idP") long idP, @RequestBody @Valid Product product) {
		try {
			Bill bill = billRepository.findBillByID(id);
			if (bill == null) {
				throw new Exception("The Bill with id:  " + id + " doesn't exist");
			}
			Product productConnect = productRepository.findProductByID(idP);
			if (productConnect == null) {
				throw new Exception("The Product with idP: " + idP + " doesn´t exist" );
			}
			productConnect.setBill(bill);
			return productRepository.save(productConnect);
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
	@GetMapping("/getbyid/{id}/products/{idP}")
	@ResponseBody
	public Product getBillProduct(@PathVariable("id") long id, @PathVariable("idP") int idP) {
		try {
			Bill bill = billRepository.findBillByID(id);
			if (bill == null) {
				throw new Exception("The Bill  with id:  " + id + " doesn't exist");
			}
			List<Product> products = bill.getProducts();
			Product productReturn = null;
			boolean foundIt = false;
			for (Product product : products) {
				if (Long.compare(product.getId(), idP) == 0) {
					foundIt = true;
				}
			}
			if (!foundIt) {
				throw new Exception("The product with id: " + idP + " doesn't have a conncetion with the bill with id: " + id);
			}
			return productReturn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/getbyid/{id}/products/allproducts")
	@ResponseBody
	public List<Product> getBillAllProducts(@PathVariable("id") long id) {
		try {
			Bill bill = billRepository.findBillByID(id);
			if (bill == null) {
				throw new Exception("The Bill  with id:  " + id + " doesn't exist");
			}
			return bill.getProducts();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
