package fr.bridgefield.brr;

import java.util.Collection;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import fr.bridgefield.brr.security.SecurityRepository;
import fr.bridgefield.brr.security.entity.AuthenticationRequest;
import fr.bridgefield.brr.security.entity.AuthenticationResponse;
import fr.bridgefield.brr.security.entity.Principal;
import fr.bridgefield.brr.security.utilities.JwtUtils;


@SpringBootApplication()
@CrossOrigin(origins = {"http://localhost:4200", "https://www.bridgefield.fr", "https://bridgefield.fr"})
@RestController
@RequestMapping("/brr")
public class StartApplication {

	public StartApplication() {
		
	}

	public static void main(String[] args) {
		
		SpringApplication.run(StartApplication.class, args);
	}
    
	@GetMapping("/hello")
    String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
      return String.format("Hello Magnificient %s!", name);
    }	



    

}
