package com.example.service.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonIgnore
	@ManyToOne
	private User user;
	private String role;

	public Role(User user, String role) {
		this.user = user;
		this.role = role;
	}

	public Role() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@JoinColumn(name = "username")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user= user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object o) {
		Role role1 = (Role) o;
		return (role1.role.equals(this.role) &&
				role1.user.getUsername().
						equals(this.user.getUsername()));
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, role);
	}

	@Override
	public String toString() {
		return "Role{" +
				"id=" + id +
				", role='" + role + '\'' +
				'}';
	}
}
