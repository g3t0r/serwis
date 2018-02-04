package com.example.service.pojo;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

	@Id
	public String username;
	public String password;

	@OneToMany(mappedBy = "user")
	private Set<Role> userRoles = new HashSet<>();




	@ColumnDefault("true")
	public boolean enabled = true;

	public User(String password, String username) {
		this.username = username;
		this.password = password;
	}


	public User() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<Role> userRoles) {
		this.userRoles = userRoles;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", userRoles=" + userRoles +
				", enabled=" + enabled +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return enabled == user.enabled &&
				Objects.equals(username, user.username) &&
				Objects.equals(password, user.password) &&
				Objects.equals(userRoles, user.userRoles);
	}

	@Override
	public int hashCode() {

		return Objects.hash(username, password, userRoles, enabled);
	}
}

