package com.example.backend.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Autowired
	private JwtHelper jwtHelper;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//getToken from header
		
		String requestToken = request.getHeader("Authorization");
		logger.info("message {}",requestToken);
		
		String username=null;
		String jwtToken = null;
		
		if(requestToken!=null && requestToken.startsWith("Bearer")) {
			jwtToken = requestToken.substring(7);
			
			try {
				username = this.jwtHelper.getUsername(jwtToken);
			}catch(ExpiredJwtException e){
				logger.info("Given jwt token is expired !!");
				
			}catch(MalformedJwtException e){
				logger.info("Some changed has done in token !! Invalid Token");
				
			}catch(IllegalArgumentException e){
				logger.info("Illegal Argument while fetching the username !!");		
			}
			
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				//validate	
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);		
				if(this.jwtHelper.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(auth);
				}else {
					logger.info("Validation fails !!");
				}
				
			}else {
				logger.info("User Message", "Username is null or auth is already there");
			}
			
		}else {
			logger.info("Token message {}", "token does not start with bearer");
		}
		filterChain.doFilter(request, response);
	}

}
