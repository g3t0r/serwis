package com.example.service.controllers;

import com.example.service.repositories.UserRepository;
import com.example.service.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UsersController {

	@Autowired
	UserRepository userRepository;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<User>> getUsers() {
		return  new ResponseEntity<List<User>>(
				userRepository.findAll(),
				new HttpHeaders(),
				HttpStatus.OK
		);
	}
}
