package com.example.service.controllers;

import com.example.service.repositories.UserRepository;
import com.example.service.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {

	private UserRepository userRepository;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(method = RequestMethod.GET)
	public String showLoginPage(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}



}
