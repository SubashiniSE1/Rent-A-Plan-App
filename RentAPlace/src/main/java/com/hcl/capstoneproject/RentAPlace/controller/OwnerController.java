package com.hcl.capstoneproject.RentAPlace.controller;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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
import com.hcl.capstoneproject.RentAPlace.model.Owner;
import com.hcl.capstoneproject.RentAPlace.model.User;
import com.hcl.capstoneproject.RentAPlace.service.OwnerService;
import com.hcl.capstoneproject.RentAPlace.service.UserService;

@RestController
@RequestMapping("/owner")
@CrossOrigin(origins = "*")
public class OwnerController {

	@Autowired
	private JavaMailSender mailSender;

	public static void sendEmail(String mailId, Message Ownermessage) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		System.out.println(40);
		mailSender.setPort(587);
		System.out.println(42);
		mailSender.setUsername("subashinisivamani@gmail.com");
		System.out.println(44);
		mailSender.setPassword("subashini99");
		System.out.println(46);

		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		System.out.println(40);
		properties.setProperty("mail.smtp.starttls.enable", "true");
		System.out.println(40);

		mailSender.setJavaMailProperties(properties);

		String from = "subashinisivamani@gmail.com";
		System.out.println(40);
		System.out.println(mailId
				+ "===============================================================234567890-234567890-234567890-");
		String to = "vishnuskk24@gmail.com";
		System.out.println(40);

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);
		System.out.println(40);
		message.setTo(to);
		System.out.println(40);
		message.setSubject("Reservation confirmation Regarding :)");
		System.out.println(40);
		message.setText("Good Morning Customer,We hopy you and Your Family Members are healthy..."+"\n Dear our Valuable Customer,Thanks for Reaching us : Your Reservation Details are  ,"
		               +"Accepting  your Reservation" + "\nCheckInDate: " + Ownermessage.getCheckInDate()
				       + "\nCheckOutDate: " + Ownermessage.getCheckOutDate() + "\nRent Amount :" + Ownermessage.getRentAmount()
				       + "\nTotal number of person: " + Ownermessage.getNoOfPersons() +"\n Stay Home, Stay Safe and healthy...."+"\n Visit our property again. Happy staying ☺☺");
		System.out.println(40);

		mailSender.send(message);
		System.out.println(message);
	}

	@Autowired
	OwnerService ownerService;
	String ownerName;

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@PostMapping("/register")
	public ResponseEntity<String> addOwner(@RequestBody Owner owner) {

		System.out.println("User is added");
		String result = ownerService.addOwner(owner);
		ResponseEntity<String> result1 = new ResponseEntity<String>(result, HttpStatus.OK);

		return result1;

	}

	@PostMapping("/login")
	public ResponseEntity<Owner> loginOwner(@RequestBody Owner owner) throws Exception {
		System.out.println("Controller");
		System.out.println(owner.getOwnerName());
		System.out.println(owner.getPassword());

		System.out.println("User is added");
		int res;
		try {
			res = ownerService.loginOwner(owner);

			if (res == 0) {
				Owner ownerResp = new Owner();

				ownerResp.setValid("valid");
				ownerResp.setOwnerName(owner.getOwnerName());

				ownerService.addOwnername(owner.getOwnerName());
				User user1 = new User();
				user1.setValid("valid");

				String msg = "SUCCESS";
				ResponseEntity<Owner> result1 = new ResponseEntity<Owner>(ownerResp, HttpStatus.OK);

				return result1;
			}
		} catch (Exception e) {
			String msg = e.getMessage();
			Owner user1 = new Owner();
			user1.setValid("invalid");
			ResponseStatusException resp = new ResponseStatusException(HttpStatus.BAD_REQUEST, msg);
			// TODO Auto-generated catch block
			throw new Exception(resp);
		}
		return null;

	}

	@PostMapping("/editOwner")
	public ResponseEntity<String> editOwner(@RequestBody Owner owner) throws Exception {

		System.out.println("User is added");
		String result = ownerService.editOwner(owner);
		ResponseEntity<String> result1 = new ResponseEntity<String>(result, HttpStatus.OK);

		return result1;

	}

	@DeleteMapping("/deleteOwner/{ownerName}")
	public ResponseEntity<String> deleteOwner(@PathVariable(value = "ownerName") String ownerName) {

		System.out.println("User is added");
		String result = ownerService.deleteOwner(ownerName);
		ResponseEntity<String> result1 = new ResponseEntity<String>(result, HttpStatus.OK);

		return result1;

	}

	@GetMapping("/ownerList")
	public ResponseEntity<List<Owner>> getOwnerList() throws Exception {
		try {

			System.out.println("login 2 created ");
			System.out.println();
			List<Owner> result = ownerService.getOwners();
			System.out.println(result);
			if (result != null) {
				System.out.println("Added successfully ");
				System.out.println(result);
				ResponseEntity<List<Owner>> result1 = new ResponseEntity<List<Owner>>(result, HttpStatus.OK);
				return result1;

			}
		} catch (Exception exception) {
			System.out.println("Adding user falied " + exception.getMessage());
			ResponseStatusException result1 = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Data");
			throw new Exception("Invalid Data", result1);

		}
		return null;
	}

	@GetMapping("/getMessage")
	public ResponseEntity<List<Message>> getOwnerMesage() throws Exception {

		List<Message> result = ownerService.getMessage(ownerService.getOwnerId());
		ResponseEntity<List<Message>> result1 = new ResponseEntity<List<Message>>(result, HttpStatus.OK);

		return result1;
	}

	@PostMapping("/Reserve")
	public ResponseEntity<String> reservePlace(@RequestBody Message message) throws Exception {
		try {
			System.out.println("i am in reserve");
			Integer result = ownerService.reservePlace(message);
			String mailId = ownerService.getMailId(message);
			sendEmail(mailId, message);

			ResponseEntity<String> result1 = new ResponseEntity<String>(result.toString(), HttpStatus.OK);

			return result1;

		} catch (Exception e) {
			System.out.println(e.getMessage()
					+ "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			ResponseStatusException result1 = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Data");
			throw new Exception("Invalid Data", result1);
		}

	}

	@PostMapping("/Reject")
	public ResponseEntity<String> rejectMessage(@RequestBody Message message) throws Exception {
		System.out.println("i am in reserve");
		Integer result = ownerService.rejectMessage(message);
		ResponseEntity<String> result1 = new ResponseEntity<String>(result.toString(), HttpStatus.OK);

		return result1;
	}

	@PostMapping("/Message")
	public ResponseEntity<String> message(@RequestBody Message message) throws Exception {

		System.out.println("i am in message");
		Integer result = ownerService.message(message);
		ResponseEntity<String> result1 = new ResponseEntity<String>(result.toString(), HttpStatus.OK);

		return result1;
	}

	@PostMapping("/MarkAsReadMessage")
	public ResponseEntity<String> MarkAsReadMessage(@RequestBody Message message) throws Exception {
//		/normal message read
		System.out.println("i am in message");
		Integer result = ownerService.markAsReaded(message);
		ResponseEntity<String> result1 = new ResponseEntity<String>(result.toString(), HttpStatus.OK);

		return result1;
	}

	@GetMapping("/getMyMessage")
	public ResponseEntity<List<Message>> getOwnerMyMesage() throws Exception {

		List<Message> result = ownerService.getMyMessage(ownerService.getOwnerId());
		ResponseEntity<List<Message>> result1 = new ResponseEntity<List<Message>>(result, HttpStatus.OK);

		return result1;
	}

}
