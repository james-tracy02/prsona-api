package prsona.controller;

import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import prsona.model.User;
import prsona.repository.UserRepository;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/users")
	public void createUser(@RequestBody User user) {
		userRepository.save(user);
	}
	
	@PostMapping("/login")
	public User login(@RequestBody User user) {
		Optional<User> userOpt = userRepository.findByUsername(user.getUsername());
		if(!userOpt.isPresent()) {
			return null;
		}
		User target = userOpt.get();
		if(target.getPassword() == user.getPassword()) {
			return target;
		}
		return null;
	}
	
	public boolean matchUser(String username, String token) {
		Optional<User> targetOpt = userRepository.findByUsername(username);
		if(!targetOpt.isPresent()) {
			return false;
		}
		User target = targetOpt.get();
		byte[] decodedBytes = Base64.getDecoder().decode(token);
		String decodedToken = new String(decodedBytes);
		String[] splitToken = decodedToken.split(":");
		return target.getUsername() == splitToken[0] 
				&& target.getPassword() == splitToken[1];
		
	}
}
