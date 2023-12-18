package fr.bridgefield.brr.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import fr.bridgefield.brr.security.utilities.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	SecurityRepository securityRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		if (header != null &&  header.substring(0, 5).equalsIgnoreCase("Bearer")) {
		
			String token = header.substring(7,header.length()-1);
			String username = jwtUtils.extractUsername(token);
			UserDetails details = securityRepository.loadUserByUsername(username);
			if( ! jwtUtils.validateToken(token, details)) throw new BadCredentialsException(" Invalid token.");
			UsernamePasswordAuthenticationToken contextToken = new UsernamePasswordAuthenticationToken(details, null,details.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(contextToken);
		}
		filterChain.doFilter(request, response);

	}

}
