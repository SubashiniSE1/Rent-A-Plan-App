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

import com.hcl.capstoneproject.RentAPlace.model.Message;
import com.hcl.capstoneproject.RentAPlace.model.Property;
import com.hcl.capstoneproject.RentAPlace.model.User;
import com.hcl.capstoneproject.RentAPlace.service.PropertyService;
import com.hcl.capstoneproject.RentAPlace.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	PropertyService propertyService;

	@PostMapping("/register")
	public ResponseEntity<String> addUser(@RequestBody User users) {

		System.out.println("User is added");
		String result = userService.addUser(users);
		ResponseEntity<String> result1 = new ResponseEntity<String>(result, HttpStatus.OK);

		return result1;

	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody User users) throws Exception {

		System.out.println("User is added");
		//int res;
		try {
		int	res = userService.loginUser(users);
			System.out.println("before add");

//			userService.addUsername(users.getUserName());
			System.out.println("afer add@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@##########################");
//			User user1 = new User();
//			user1.setValid("valid");

			String msg = "SUCCESS";
			ResponseEntity<String> result1 = new ResponseEntity<String>(msg, HttpStatus.OK);

			return result1;

		} catch (Exception e) {
			String msg = e.getMessage();
			User user1 = new User();
			user1.setValid("invalid");
			ResponseStatusException resp = new ResponseStatusException(HttpStatus.BAD_REQUEST, msg);

			throw new Exception(resp);
		}

	}

	@PostMapping("/editUser")
	public ResponseEntity<String> editUser(@RequestBody User users) throws Exception {

		System.out.println("User is added");
		String result = userService.editUser(users);
		ResponseEntity<String> result1 = new ResponseEntity<String>(result, HttpStatus.OK);

		return result1;

	}

	@DeleteMapping("/deleteUser/{userName}")
	public ResponseEntity<String> deleteUser(@PathVariable(value = "userName") String userName) {

		System.out.println("User is added");
		String result = userService.deleteUser(userName);
		ResponseEntity<String> result1 = new ResponseEntity<String>(result, HttpStatus.OK);

		return result1;

	}

	@GetMapping("/userList")
	public ResponseEntity<List<User>> getUserList() throws Exception {
		try {

			System.out.println("login 2 created ");
			System.out.println();
			List<User> result = userService.getUsers();
			System.out.println(result);
			if (result != null) {
				System.out.println("Added successfully ");
				System.out.println(result);
				ResponseEntity<List<User>> result1 = new ResponseEntity<List<User>>(result, HttpStatus.OK);
				return result1;

			}
		} catch (Exception exception) {
			System.out.println("Adding user falied " + exception.getMessage());
			ResponseStatusException result1 = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Data");
			throw new Exception("Invalid Data", result1);

		}
		return null;
	}

	@GetMapping("/getPropertyList")
	public ResponseEntity<List<Property>> getPropertyList() throws Exception {
		try {

			System.out.println("login 2 created ");
			System.out.println();
			List<Property> result = propertyService.getProperties();
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

	@PostMapping("/selectedProperty")
	public ResponseEntity<String> setProperty(@RequestBody Property property) throws Exception {
		int res = propertyService.setProperty(property);
		return null;

	}

	@PostMapping("/reserveProperty")
	public ResponseEntity<String> reserveProperty(@RequestBody Message message) throws Exception {
		System.out.println("I am in controller");

		int res = userService.reserveProperty(message);
		ResponseEntity<String> result1 = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return result1;

	}

}
