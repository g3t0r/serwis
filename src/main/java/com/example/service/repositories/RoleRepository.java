package com.example.service.repositories;

import com.example.service.pojo.Role;
import com.example.service.pojo.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends CrudRepository<Role, Long> {
	public Set<Role> getRolesByUser(User user);
}
