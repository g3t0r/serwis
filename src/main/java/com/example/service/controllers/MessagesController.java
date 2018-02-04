package com.example.service.controllers;

import com.example.service.pojo.Message;
import com.example.service.pojo.User;
import com.example.service.repositories.MessageRepository;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/messages")
public class MessagesController {

	@Autowired
	MessageRepository messageRepository;

	@GetMapping
	public ResponseEntity<List<Message>> getMessagesOfCurrentlyLoggedUser(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("message" ,new Message());
		return new ResponseEntity<List<Message>>(
			messageRepository.getMessagesBySender(user),
			new HttpHeaders(),
			HttpStatus.OK
		);
	}


}
