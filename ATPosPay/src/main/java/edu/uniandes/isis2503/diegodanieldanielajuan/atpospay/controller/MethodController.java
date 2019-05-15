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

import edu.uniandes.isis2503.diegodanieldanielajuan.atpospay.entity.Method;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpospay.repository.MethodRepository;

@Controller
@RequestMapping("/methods")
public class MethodController {

	
	/**
	 * Connection to the Repository of method
	 */
	@Autowired
	private MethodRepository methodRepository;
	

	/**
	 * /methods/create -> create a new method and save it in the database.
	 * 
	 * @return a string describing if the method is succesfully created or not.
	 */
	@PostMapping("/create")
	@ResponseBody
	public Method createmethod(@Valid @RequestBody Method method) {
		try {
			return methodRepository.save(method);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * /methods/update/{id} -> updated the methods with passed
	 * 
	 * @return A method updated
	 */
	@PutMapping("/update/{id}")
	@ResponseBody
	public ResponseEntity<Method> updatemethod(@PathVariable("id") long id, @Valid @RequestBody Method method) {
		try {
			Method oldmethod = methodRepository.findMethodByID(id);
			if (oldmethod == null) {
				throw new Exception("The method with id: " + id + " doesn´t exist in our database");
			}
			oldmethod.setName(method.getName());
			oldmethod.setDescription(method.getDescription());
			final Method updatemethod = methodRepository.save(oldmethod);
			return ResponseEntity.ok(updatemethod);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * /methods/getbyid/{id} -> return the method having the passed id.
	 * 
	 * @param id The id to search in the database.
	 * @return method with the id
	 */
	@GetMapping("/getbyid/{id}")
	@ResponseBody
	public Method getmethodID(@PathVariable("id") long id) {
		try {
			Method method = methodRepository.findMethodByID(id);
			if (method == null) {
				throw new Exception("The method with id: " + id + " doesn´t exist in our database");
			}
			return method;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /methods/allmethods -> return all the methods..
	 * 
	 * @return a empty list.
	 */
	@ResponseBody
	@GetMapping("/allmethods")
	public List<Method> getmethods() {
		try {
			List<Method> method = methodRepository.getAllMethods();
			return method;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * /methods/delete/{id} -> delete the method having the passed id.
	 * 
	 * @param id The id for the methods to delete
	 * @return A string describing if the methods is succesfully deleted or not.
	 */
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deletemethod(@PathVariable("id") long id) {
		try {
			Method methodDelete = methodRepository.findMethodByID(id);
			if (methodRepository.findMethodByID(id) == null) {
				throw new Exception("The method with id: " + id + " doesn´t exist in our database");
			}
			methodRepository.delete(methodDelete);
		} catch (Exception ex) {
			return "Error deleting the method:" + ex.toString();
		}
		return "method succesfully deleted with id : " + id;
	}
	
	
}
