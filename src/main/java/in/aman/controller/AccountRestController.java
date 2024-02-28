package in.aman.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.aman.bindings.UserAccForm;
import in.aman.services.AccountService;

@RestController
public class AccountRestController {

	private Logger logger = LoggerFactory.getLogger(AccountRestController.class);

	@Autowired
	private AccountService accountService;

	@PostMapping("/user")
	public ResponseEntity<String> createAccount(@RequestBody UserAccForm userAccForm) {

		logger.debug("Account Creation process Started");

		boolean status = accountService.createUserAccount(userAccForm);

		logger.debug("Account Creation process complete");

		if (status) {
			logger.info("Account Created successfully");

			return new ResponseEntity<>("Account Created", HttpStatus.CREATED);
		} else {
			logger.info("Account Creation Failed");

			return new ResponseEntity<>("Account Creation Failed", HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@GetMapping("/users")
	public ResponseEntity<List<UserAccForm>> getUsers() {

		logger.debug("Fetching user account process started");

		List<UserAccForm> userAccForms = accountService.fetchUserAccounts();

		logger.debug("Fetching user account process complete");

		logger.info("User Accounts Fetched success");

		return new ResponseEntity<>(userAccForms, HttpStatus.OK);

	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<UserAccForm> getUser(@PathVariable Integer userId) {

		UserAccForm userAccById = accountService.getUserAccById(userId);
		logger.info("User Account Fetched successfully");

		return new ResponseEntity<>(userAccById, HttpStatus.OK);

	}

	@PutMapping("/user/{userId}/{status}")
	public ResponseEntity<List<UserAccForm>> updateUserAcc(@PathVariable Integer userId, @PathVariable String status) {

		logger.debug("User acoount update process started");
		
		accountService.changeAccStatus(userId, status);

		logger.debug("User acoount updation completed");

		
		logger.info("User Account Status update successfully");

		List<UserAccForm> userAccForms = accountService.fetchUserAccounts();

		return new ResponseEntity<>(userAccForms, HttpStatus.OK);

	}

}
