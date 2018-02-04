package com.example.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource dataSource;


	public void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/","/rejestracja/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin().loginPage("/login")
				.permitAll()
				.and()
				.logout().permitAll();

		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());


	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, enabled from users where username=?")
				.authoritiesByUsernameQuery("select user_username, role from user_roles where user_username=?");
	}
}
