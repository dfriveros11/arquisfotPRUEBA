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

import edu.uniandes.isis2503.diegodanieldanielajuan.atpospay.entity.Pay;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpospay.entity.Method;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpospay.entity.Buyer;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpospay.repository.PayRepository;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpospay.repository.MethodRepository;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpospay.repository.BuyerRepository;

@Controller
@RequestMapping("/{applicationName}")
public class PayController {

	/**
	 * Connection to the Repository of pay
	 */
	@Autowired
	private PayRepository payRepository;
	
	@Autowired
	private BuyerRepository buyerRepository;
	
	@Autowired
	private MethodRepository methodRepository;

	/**
	 * /Pays/create -> create a new Pay and save it in the database.
	 * 
	 * @return a string describing if the Pay is succesfully created or not.
	 */
	@PostMapping("/create")
	@ResponseBody
	public Pay createPay(@Valid @RequestBody Pay Pay) {
		try {
			return payRepository.save(Pay);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * /Pays/update/{id} -> updated the Pays with passed
	 * 
	 * @return A Pay updated
	 */
	@PutMapping("/update/{id}")
	@ResponseBody
	public ResponseEntity<Pay> updatePay(@PathVariable("id") long id, @Valid @RequestBody Pay Pay) {
		try {
			Pay oldPay = payRepository.findPayByID(id);
			if (oldPay == null) {
				throw new Exception("The Pay with id: " + id + " doesn´t exist in our database");
			}
			oldPay.setValue(Pay.getValue());
			oldPay.setDescription(Pay.getDescription());
			oldPay.setName(Pay.getName());
			oldPay.setValue(Pay.getValue());
			oldPay.setMethod(Pay.getMethod());
			oldPay.setBuyer(Pay.getBuyer());
			final Pay updatePay = payRepository.save(oldPay);
			return ResponseEntity.ok(updatePay);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * /Pays/getbyid/{id} -> return the Pay having the passed id.
	 * 
	 * @param id The id to search in the database.
	 * @return Pay with the id
	 */
	@GetMapping("/getbyid/{id}")
	@ResponseBody
	public Pay getPayID(@PathVariable("id") long id) {
		try {
			Pay Pay = payRepository.findPayByID(id);
			if (Pay == null) {
				throw new Exception("The Pay with id: " + id + " doesn´t exist in our database");
			}
			return Pay;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /Pays/allPays -> return all the Pays..
	 * 
	 * @return a empty list.
	 */
	@ResponseBody
	@GetMapping("/allpays")
	public List<Pay> getPays() {
		try {
			List<Pay> Pays = payRepository.getAllPays();
			return Pays;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /Pays/delete/{id} -> delete the Pay having the passed id.
	 * 
	 * @param id The id for the Pays to delete
	 * @return A string describing if the Pay is succesfully deleted or not.
	 */
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deletePay(@PathVariable("id") long id) {
		try {
			Pay PayDelete = payRepository.findPayByID(id);
			if (payRepository.findPayByID(id) == null) {
				throw new Exception("The Pay with id: " + id + " doesn´t exist in our database");
			}
			payRepository.delete(PayDelete);
		} catch (Exception ex) {
			return "Error deleting the Pay:" + ex.toString();
		}
		return "Pay succesfully deleted with id : " + id;
	}
	
	/* CONNECTION WITH BUYER */
	
	@PostMapping("create/{id}/buyers")
	@ResponseBody
	public Buyer createPayBuyer(@PathVariable("id") long id, @RequestBody @Valid Buyer buyer) {
		try {
			Pay Pay = payRepository.findPayByID(id);
			if (Pay == null) {
				throw new Exception("The Pay with id:  " + id + " doesn't exist");
			}
			Buyer buyerTest = Pay.getBuyer();
			if (buyerTest != null) {
				throw new Exception("The Pay with id: " + id + " has already a buyer");
			}
			buyer.setPay(Pay);
			return buyerRepository.save(buyer);
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
	@GetMapping("/getbyid/{id}/buyers/{idR}")
	@ResponseBody
	public Buyer getPaybuyer(@PathVariable("id") long id, @PathVariable("idR") int idR) {
		try {
			Pay Pay = payRepository.findPayByID(id);
			if (Pay == null) {
				throw new Exception("The Pay  with id:  " + id + " doesn't exist");
			}
			Buyer buyer = Pay.getBuyer();
			if(buyer == null) {
				throw new Exception("buyer doesn't exist");
			}
			if (Long.compare(buyer.getId(), idR) != 0) {
				throw new Exception("buyer with that id:" + idR + "doesn't exist");
			}
			return buyer;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/* CONNECTION WITH METHOD */
	
	@PostMapping("create/{id}/methods/{idP}")
	@ResponseBody
	public Method createPaymethod(@PathVariable("id") long id, @PathVariable("idP") long idP, @RequestBody @Valid Method method) {
		try {
			Pay Pay = payRepository.findPayByID(id);
			if (Pay == null) {
				throw new Exception("The Pay with id:  " + id + " doesn't exist");
			}
			Method methodConnect = methodRepository.findMethodByID(idP);
			if (methodConnect == null) {
				throw new Exception("The method with idP: " + idP + " doesn´t exist" );
			}
			methodConnect.setPay(Pay);
			return methodRepository.save(methodConnect);
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
	@GetMapping("/getbyid/{id}/methods/{idP}")
	@ResponseBody
	public Method getPaymethod(@PathVariable("id") long id, @PathVariable("idP") int idP) {
		try {
			Pay Pay = payRepository.findPayByID(id);
			if (Pay == null) {
				throw new Exception("The Pay  with id:  " + id + " doesn't exist");
			}
			Method method = Pay.getMethod();
			
			if (Long.compare(method.getId(), idP) == 0) {
				throw new Exception("The method with id: " + idP + " doesn't have a conncetion with the Pay with id: " + id);
			}

			return method;
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
	@GetMapping("/getbyid/{id}/methods/allmethods")
	@ResponseBody
	public Method getPayAllmethods(@PathVariable("id") long id) {
		try {
			Pay Pay = payRepository.findPayByID(id);
			if (Pay == null) {
				throw new Exception("The Pay  with id:  " + id + " doesn't exist");
			}
			return Pay.getMethod();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
