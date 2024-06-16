package com.example.backend.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.backend.Security.JwtAuthenticationEntryPoint;
import com.example.backend.Security.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfid extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailService customUserDetailsService;
	@Autowired
	private JwtAuthenticationFilter filter;
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	@Autowired
	private UserDetailsService userDetailsService;
	
	public static String[] public_url = {"/auth/login", "/user/create"};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.antMatchers(public_url)
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(this.entryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http
		.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
		}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.customUserDetailsService).passwordEncoder(this.passwordEncoder());
	}
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception{
		return super.authenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public FilterRegistrationBean cosFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.addAllowedOriginPattern("*");
		configuration.addAllowedHeader("Authorization");
		configuration.addAllowedHeader("Context-Type");
		configuration.addAllowedHeader("Accept");
		configuration.addAllowedHeader("POST");
		configuration.addAllowedHeader("GET");
		configuration.addAllowedHeader("PUT");
		configuration.addAllowedHeader("DELETE");
		configuration.addAllowedHeader("OPTIONS");
		configuration.setMaxAge(3600L);;
		source.registerCorsConfiguration("/**", configuration);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(-110);
		return bean;
	}

	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		.csrf()
//		.disable()
//		.authorizeHttpRequests()
//		.antMatchers(public_url)
//		.permitAll()
//		.anyRequest()
//		.authenticated()
//		.and()
//		.httpBasic();
//		}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(this.customUserDetailsService).passwordEncoder(this.passwordEncoder());
//	}
//	
//	//thakur
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

}
