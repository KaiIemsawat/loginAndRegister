package com.mvc.registration.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mvc.registration.models.LogUser;
import com.mvc.registration.models.User;
import com.mvc.registration.services.UserService;

@Controller
public class MainController {

	/*
	  Visible routes: 
	  	/dashboard
	  
	  hidden routes: 
	  	/register - POST request route
	  	/login - POST request route
	  	/logout - GET request route
	 */
	
	@Autowired
	private UserService uServ;
	
	
//	Login/Registration page
	@GetMapping("/")
	public String loginRegPage(
			@ModelAttribute("registerUser") User newUser
			, @ModelAttribute("logUser") LogUser logUser
			) {
		return "loginreg";
	}
	
//	Dashboard - must be logged in
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("loggedUser", uServ.findUserById(userId));
		return "dashboard";
	}
	
//	Register user if valid
	@PostMapping("/register")
	public String registerUser(
			@ModelAttribute("logUser") LogUser logUser
			, @Valid @ModelAttribute("registerUser") User newUser
			, BindingResult result
			, HttpSession session
			) {
		
		User changedUser = uServ.register(newUser, result);
//		Perform additional validation (that was add in Service)
		if(result.hasErrors()) {
			return "loginreg";
		}
//		User is already registered in the register method in the service file
		session.setAttribute("userId", changedUser.getId());
		return "redirect:/dashboard";
	}
	
//	Login user if valid
	@PostMapping("/login")
	public String loginUser(
			@Valid @ModelAttribute("logUser") LogUser logUser
			, BindingResult result
			, HttpSession session
			, @ModelAttribute("registerUser") User newUser
			) {
		User foundUser = uServ.login(logUser, result);
		if(result.hasErrors()) {
			return "loginreg";
		}
		session.setAttribute("userId", foundUser.getId());
		return "redirect:/dashboard";
	}
	
//	Log out by clearing session and sending back to login/register page
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}
