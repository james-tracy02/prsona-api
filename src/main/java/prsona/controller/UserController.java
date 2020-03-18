package prsona.controller;

import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/users")
	public void createUser(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
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
				&& passwordEncoder.matches(splitToken[1], target.getPassword());
		
	}
}
