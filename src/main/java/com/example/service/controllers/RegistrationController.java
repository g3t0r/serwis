package com.example.service.controllers;

import com.example.service.repositories.RoleRepository;
import com.example.service.pojo.Role;
import com.example.service.pojo.User;
import com.example.service.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/rejestracja")
public class RegistrationController {


	@Autowired
	public UserRepository userRepository;

	@Autowired
	public RoleRepository roleRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(method = RequestMethod.POST)
	public String registerNewUserForm(@ModelAttribute User user) {
		logger.info(user.toString());
		User tmp = userRepository.findOneByUsername(user.getUsername());
		if (tmp == null) {
			userRepository.save(new User(user.getPassword(), user.getUsername()));
			roleRepository.save(new Role(user, "ROLE_USER"));
			return "home";
		}
		else {
			return new Exception().getMessage();
		}
	}


	@RequestMapping(value = "/json", method = RequestMethod.POST)
	public String registerNewUserJson(@RequestBody User user) {
		userRepository.save(user);
		roleRepository.save(new Role(user, "ROLE_USER"));
		return "home";
	}



	@RequestMapping(method = RequestMethod.GET)
	public String showRegisterPage(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}


}
