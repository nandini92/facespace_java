package com.facespace.facespace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@SpringBootApplication
@RestController
public class FacespaceApplication {
	public static Utils util= new Utils();

	public static void main(String[] args) {
		util.loadCustomersFromJson();
		SpringApplication.run(FacespaceApplication.class, args);
	}

	record UsersResponse(
		int status,
		ArrayList<User> data,
		String message
	){}

	@GetMapping("/api/users")
	public UsersResponse getUsers(){
		System.out.println("Serving request: getUsers()");
		ArrayList<User> users = util.getUsers();


		System.out.println("Sending response: " + users);

		return new UsersResponse(200, users, "No message included");
	}

	record UserResponse(
		int status,
		User data,
		String message
	){}

	@GetMapping("/api/users/{id}")
	public UserResponse getUserById(@PathVariable("id") int id){
		System.out.println("Serving request: getUserById() for " + id);
		User searchedUser = util.findUser(id);

		System.out.println("Sending response: " + searchedUser);

		return new UserResponse(200, searchedUser, "No message included");

	}

	@PostMapping("/api/signin")
	public UserResponse handleSignIn(@RequestBody String user){
		System.out.println("Serving request: handleSignIn() for " + user);
		User signedIn = util.findUserName(user);

		System.out.println("Sending response: " + signedIn);

		return new UserResponse(200, signedIn, "No message included");
	}
}
