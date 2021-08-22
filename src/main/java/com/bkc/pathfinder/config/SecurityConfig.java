package com.bkc.pathfinder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bkc.pathfinder.config.security.PFUserDetailsService;
import com.bkc.pathfinder.config.security.jwt.JwtAuthorizationFilter;
import com.bkc.pathfinder.config.security.PFPasswordEncoder;

/**
 * 
 * @author bumki
 *
 */

/*
 * WebSecurityConfigurerAdapter configures AuthenticationManagerBuilder by providing UserDetailsService/PasswordEncoder
 * It also configures HttpSecurity
 * HttpSecurity config handles cors, csrf, sessionManagement, jwtFilterProvider
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PFPasswordEncoder pfPasswordEncoder;

	@Autowired
	private PFUserDetailsService pfUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//This calls DaoAuthenticationProvider.additionalAuthenticationChecks to compare password
		auth.userDetailsService(pfUserDetailsService).passwordEncoder(pfPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors(); // cross-origin resource sharing
		http.csrf().disable(); // disabling cross site request forgery. this attack is on cookies and using JWT prevents it.
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //app will not create session. every request will need to be authenticated. using JWT makes it okay?

		http.authorizeRequests()
	        .antMatchers("/api/authentication/**").permitAll()
	        .anyRequest().authenticated();

//		http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN").antMatchers("/user").hasAnyRole("ADMIN", "USER")
//				.antMatchers("/").permitAll().and().formLogin();
		
		http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
			}
		};
	}

	@Bean
	public JwtAuthorizationFilter jwtAuthorizationFilter() {
		return new JwtAuthorizationFilter();
	}

}
