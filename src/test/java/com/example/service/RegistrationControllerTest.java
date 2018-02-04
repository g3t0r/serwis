package com.example.service;

import com.example.service.controllers.RegistrationController;
import com.example.service.pojo.Role;
import com.example.service.pojo.User;
import com.example.service.repositories.RoleRepository;
import com.example.service.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class RegistrationControllerTest {
	@Test
	public void isReturningViewOnRequest() throws Exception {
		RegistrationController registrationController = new RegistrationController();
		MockMvc mockMvc = standaloneSetup(registrationController).build();
		mockMvc.perform(get("/rejestracja")).andExpect(view().name("registration"));
	}

	@Test
	public void registrationByForm() throws Exception {
		UserRepository userRepository = mock(UserRepository.class);
		RoleRepository roleRepository = mock(RoleRepository.class);
		User unsaved = new User("password", "username");
		User saved = new User("password", "username");
		Role role = new Role(saved, "ROLE_USER");
		when(userRepository.save(unsaved)).thenReturn(saved);
		when(roleRepository.save(role)).thenReturn(role);

		RegistrationController mockController = new RegistrationController();
		mockController.userRepository = userRepository;
		mockController.roleRepository = roleRepository;
		Assert.assertNotNull(mockController);
		MockMvc mockMvc = standaloneSetup(mockController).build();
		mockMvc.perform(post("/rejestracja")
				.param("username", "username")
				.param("password", "password"))
				.andExpect(view().name("home"));
		verify(userRepository, atLeastOnce()).save(unsaved);
		verify(roleRepository, atLeastOnce()).save(role);
	}


	@Test
	public void registrationByJson() throws Exception {
		UserRepository userRepository = mock(UserRepository.class);
		User unsaved = new User("password", "username");
		User saved = new User("password", "username");
		when(userRepository.save(unsaved)).thenReturn(saved);

		RegistrationController controller = new RegistrationController();
		controller.userRepository = userRepository;
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(post("/rejestracja/json")
				.content("{" +
						"\"password\":\"password\", " +
						"\"username\":\"username\"" +
						"}").contentType("application/json"))
				.andExpect(view().name("home"));
		verify(userRepository, atLeastOnce()).save(unsaved);

	}
}
