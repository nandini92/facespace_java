package com.facespace.facespace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@SpringBootApplication
@CrossOrigin
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

	@GetMapping("/api/users/")
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

	@DeleteMapping("/api/users/{id}")
	public UserResponse deleteUser(int id){
		System.out.println("Serving request: deleteUser() for " + id);

		boolean response = util.deleteUser(id);

		if(response){
			return new UserResponse(200, null, "user deleted");
		} else {
			return new UserResponse(404, null, "User not found");
		}
	}

	@PutMapping("/api/users")
	public UserResponse updateUser(@RequestBody int id, String name, ArrayList<Integer> friends, String avatarUrl){

		User user = util.findUser(id);

		user.setId(id);
		user.setName(name);
		user.setFriends(friends);
		user.setAvatarUrl(avatarUrl);

		return new UserResponse(200, user, "User updated");
	}

	record FriendResponse(
		int status,
		String[] friends,
		String message
	){}

	@PatchMapping("/api/friends")
	public FriendResponse handleFriends(@RequestBody int[] newFriends){
		User user1 = util.findUser(newFriends[0]);
		User user2 = util.findUser(newFriends[1]);

		if( !util.findFriend(user1.getFriends(), newFriends[1])){
			user1.addFriend(newFriends[1]);
		}

		if( !util.findFriend(user2.getFriends(), newFriends[0])){
			user2.addFriend(newFriends[0]);
		}

		String[] results = { user1.getFriends().toString(), user2.getFriends().toString()};

		return new FriendResponse(200, results, "Users are now friend");
	}
}
