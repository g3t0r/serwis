package com.example.service.repositories;

import com.example.service.pojo.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findByUsername(String username);
	User findOneByUsername(String username);
	List<User> findAll();
}
