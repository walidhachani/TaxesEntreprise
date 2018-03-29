package com.m2i.formation.securite;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	 private DataSource dataSource ; 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		auth.inMemoryAuthentication().withUser("walid").password("123").roles("ADMIN" , "USER") ;
		auth.inMemoryAuthentication().withUser("hachani").password("123").roles("USER") ;
	    */
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username as principal , pass as credentials , active from users where username = ?")
		.authoritiesByUsernameQuery("select username as principal , role as role  from users_roles where username = ?")
		.rolePrefix("ROLE_")
		.passwordEncoder(new Md5PasswordEncoder());
		
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login") ; 
		http.authorizeRequests().antMatchers("/saveEntreprise","/formEntreprise").hasRole("ADMIN") ; 
		http.authorizeRequests().antMatchers("/entreprises" , "/taxes" ).hasRole("USER") ;
		http.exceptionHandling().accessDeniedPage("/403") ;
		
	}
}
