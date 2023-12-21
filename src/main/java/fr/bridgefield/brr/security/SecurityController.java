package fr.bridgefield.brr.security;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.bridgefield.brr.security.entity.AuthenticationRequest;
import fr.bridgefield.brr.security.entity.AuthenticationResponse;
import fr.bridgefield.brr.security.entity.Exception;
import fr.bridgefield.brr.security.entity.Principal;
import fr.bridgefield.brr.security.entity.SecurityException;
import fr.bridgefield.brr.security.utilities.JwtUtils;

@CrossOrigin(origins = {"http://localhost:4200", "https://www.bridgefield.fr", "https://bridgefield.fr"})
@RestController
@RequestMapping("/auth")
public class SecurityController {

	

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private SecurityRepository securityPepository;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@CrossOrigin
	@PostMapping("/login")
	ResponseEntity<?> login(@RequestBody AuthenticationRequest body) throws SecurityException{
		Principal p;
		try {
			
			p = ( Principal ) securityPepository.loadUserByUsername(body.getUserName());
			authManager.authenticate(new UsernamePasswordAuthenticationToken(body.getUserName(), body.getPassword()));
		} catch (BadCredentialsException bce) {
			// TODO Auto-generated catch block
			SecurityException e = new SecurityException();
			e.setLocalizedMessage(SecurityException.FR_BRIDGEFIELD_BADCREDENTIALS,body.getUserName());
			throw (e);
		}
		catch (UsernameNotFoundException unfe) {
			SecurityException e = new SecurityException();
			e.setLocalizedMessage(SecurityException.FR_BRIDGEFIELD_PRINCIPAL_NOT_FOUND, body.getUserName());
			throw (e);
		}
		
		
		
		AuthenticationResponse token = new AuthenticationResponse(jwtUtils.generateToken(p.getUsername()), p);
		return ResponseEntity.ok(token);
	}	
	
}
