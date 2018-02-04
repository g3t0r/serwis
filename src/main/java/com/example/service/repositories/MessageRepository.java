package com.example.service.repositories;

import com.example.service.pojo.Message;
import com.example.service.pojo.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository <Message, Long>{
	public List<Message> getMessagesBySenderAndReceiver(User sender, User receiver);
	public List<Message> getMessagesBySender(User sender);
}
