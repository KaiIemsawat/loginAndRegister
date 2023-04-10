package com.mvc.registration.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.mvc.registration.models.LogUser;
import com.mvc.registration.models.User;
import com.mvc.registration.repositories.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	public User register(User newUser, BindingResult result) {
//		Check to see if the passwords agree
		if(!newUser.getConfirm().equals(newUser.getPassword())) {
			result.rejectValue("confirm password", "Match", "Your must confirm password corectly");
		}
//		Check if we have any user with the email already
		Optional<User> optionalUser = userRepo.findByEmail(newUser.getEmail());
		if(optionalUser.isPresent()) {
			result.rejectValue("email", "Match", "This email is already registered.");
		}
//		Return the user back if the validation went good
		if(result.hasErrors()) {
			return null;
		}
		else // Means, at this point, all validations are good
		{
//			Hash the password. Then, assign with setter
			String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			newUser.setPassword(hashedPassword); 
			return userRepo.save(newUser);
		}
	}
	
	public User login(LogUser logUser, BindingResult result) {
//		Check to see if the given is in the database
		Optional<User> optionalUser = userRepo.findByEmail(logUser.getLogEmail());
		if(!optionalUser.isPresent()) { // If user can't be found
							//  "logEmail" <---- need to be the same as 'path' in .jsp file and variable name in model class
			result.rejectValue("logEmail", "Matchs", "Invalid login credentials");
			//  				"logPassword" <---- need to be the same as 'path' in .jsp file and variable name in model class
			result.rejectValue("logPassword", "Matchs", "Invalid login credentials");
			return null; // Stop from checking password
		}
		User thisUser = optionalUser.get(); // Return actual user who has the matched  email

		if (!BCrypt.checkpw(logUser.getLogPassword(), thisUser.getPassword())) { // Password don't match
							//  "logEmail" <---- need to be the same as 'path' in .jsp file and variable name in model class
			result.rejectValue("logEmail", "Matchs", "Invalid login credentials");
							//  "logPassword" <---- need to be the same as 'path' in .jsp file and variable name in model class
			result.rejectValue("logPassword", "Matchs", "Invalid login credentials");
			return null; // Can't return the user - Invalid log in
		}
		return thisUser;
	}
	
	public User findUserById(Long id) {
		// can be replaced with the one in findByEmail()
		return userRepo.findById(id).orElse(null); 
	}
	
	public User findByEmail(String email) {
		// can be replaced with the one in findById()
		Optional<User> optionalUser = userRepo.findByEmail(email);
		return optionalUser.orElseGet(()->null);
	}
	
	public User updateUser(User user) {
		return userRepo.save(user);
	}
	
	public void deleteUserUsingId(Long id) {
		userRepo.deleteById(id);
	}
}
