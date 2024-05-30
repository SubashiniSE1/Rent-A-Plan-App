
package com.hcl.capstoneproject.RentAPlace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.hcl.capstoneproject.RentAPlace.model.Property;
import com.hcl.capstoneproject.RentAPlace.model.User;
import com.hcl.capstoneproject.RentAPlace.service.PropertyService;

@RestController
@RequestMapping("/property")
@CrossOrigin(origins = "*")
public class PropertyController {

	@Autowired
	PropertyService propertyService;

	@PostMapping("/addingProperty")
	public ResponseEntity<String> addProperty(@RequestBody Property property) {
		String OwnerName = propertyService.getOwnerName();
		int ownerId = propertyService.getOwnerID(OwnerName);
		property.setOwnerId(ownerId);
		System.out.println("User is added");
		String result = propertyService.addProperty(property);
		ResponseEntity<String> result1 = new ResponseEntity<String>(result, HttpStatus.OK);

		return result1;

	}

	@PostMapping("/editProperty")
	public ResponseEntity<String> editUser(@RequestBody Property property) throws Exception {
		try {
			System.out.println("User is added");
			System.out.println(property);
			int result = propertyService.editProperty(property);
			System.out.println(result);
			// ResponseEntity<String> result1 = new ResponseEntity<String>(result,
			// HttpStatus.OK);

			// return result1;

			if (result == 1) {
				System.out.println("Added successfully ");
				ResponseEntity<String> result1 = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
				return result1;

			}
		} catch (Exception exception) {
			System.out.println("Property U[dation Failed falied " + exception.getMessage());
			ResponseStatusException result1 = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Data");
			throw new Exception("Invalid Data", result1);

		}
		return null;
	}

	@PostMapping("/deleteProperty")
	public ResponseEntity<String> deleteUser(@RequestBody Property property) {

		System.out.println("User is added");
		String result = propertyService.deleteProperty(property.getPropertyId());
		ResponseEntity<String> result1 = new ResponseEntity<String>(result, HttpStatus.OK);

		return result1;

	}

	@GetMapping("/getPropertyList")
	public ResponseEntity<List<Property>> getPropertyList() throws Exception {
		try {

			System.out.println("login 2 created ");
			String OwnerName = propertyService.getOwnerName();
			System.out.println();
			List<Property> result = propertyService.getOwnerProperties(OwnerName);
//			System.out.println(result);
			if (result != null) {
				System.out.println("Added successfully ");
				System.out.println(result);
				ResponseEntity<List<Property>> result1 = new ResponseEntity<List<Property>>(result, HttpStatus.OK);
				return result1;

			}
		} catch (Exception exception) {
			System.out.println("Adding user falied " + exception.getMessage());
			ResponseStatusException result1 = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Data");
			throw new Exception("Invalid Data", result1);

		}
		return null;
	}

	@GetMapping("/getTopRatedProperties")
	public ResponseEntity<List<Property>> getTopRatedPropertyList() throws Exception {
		try {

			System.out.println("login 2 created ");
			System.out.println();
			List<Property> result = propertyService.getTopRatedProperties();
			System.out.println(result);
			if (result != null) {
				System.out.println("Added successfully ");
				System.out.println(result);
				ResponseEntity<List<Property>> result1 = new ResponseEntity<List<Property>>(result, HttpStatus.OK);
				return result1;

			}
		} catch (Exception exception) {
			System.out.println("Adding user falied " + exception.getMessage());
			ResponseStatusException result1 = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Data");
			throw new Exception("Invalid Data", result1);

		}
		return null;
	}

	@GetMapping("/searchPropertyByCategory")
	public ResponseEntity<String> getPropertyByCategory(@RequestBody Property property) throws Exception {
		try {

			System.out.println("login 2 created ");
			System.out.println();
			int result = propertyService.getPropertyByCategory(property);
			System.out.println(result);
			if (result != 0) {
				System.out.println("searched successfully ");
				System.out.println(result);
				ResponseEntity<String> result1 = new ResponseEntity<String>(HttpStatus.OK);
				return result1;

			}
		} catch (Exception exception) {
			System.out.println("Search falied " + exception.getMessage());
			ResponseStatusException result1 = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Data");
			throw new Exception("Invalid Data", result1);

		}
		return null;
	}

	@PostMapping("/searchPropertyByAddress")
	public ResponseEntity<List<Property>> getPropertyByAddress(@RequestBody Property property) throws Exception {
		try {

			System.out.println("login 2 created ");
			System.out.println();
			List<Property> result = propertyService.getPropertyByAddress(property);
			System.out.println(result);
			if (!(result.isEmpty())) {
				System.out.println("searched successfully ");
				System.out.println(result);
				ResponseEntity<List<Property>> result1 = new ResponseEntity<List<Property>>(result, HttpStatus.OK);
				return result1;

			}
		} catch (Exception exception) {
			System.out.println("Search falied " + exception.getMessage());
			ResponseStatusException result1 = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Data");
			throw new Exception("Invalid Data", result1);

		}
		return null;
	}

	@GetMapping("/ownerName")
	public ResponseEntity<String> getUserName() throws Exception {
		try {
			System.out.println("login 2 created ");
			System.out.println();
			OwnerController ownerController = new OwnerController();
			String ownerName = ownerController.getOwnerName();
			System.out.println(ownerName);
			ResponseEntity<String> result1 = new ResponseEntity<String>(ownerName, HttpStatus.OK);
			return result1;

		} catch (Exception exception) {
			System.out.println("Adding food item falied " + exception.getMessage());
			ResponseStatusException result1 = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Data");
			throw new Exception("Invalid Data", result1);

		}

	}

	@GetMapping("/getProperty")
	public ResponseEntity<Property> getProperty() throws Exception {
		Property pro = propertyService.getProperty();
		ResponseEntity<Property> result1 = new ResponseEntity<Property>(pro, HttpStatus.OK);
		return result1;
	}

}
