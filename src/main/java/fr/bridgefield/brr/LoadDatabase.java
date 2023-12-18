package fr.bridgefield.brr;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.bridgefield.brr.dao.EmployeeRepository;
import fr.bridgefield.brr.dao.entity.Employee;
import fr.bridgefield.brr.security.SecurityRepository;
import fr.bridgefield.brr.security.SecurityRepository.SecurityException;
import fr.bridgefield.brr.security.dao.AuthorityRepository;
import fr.bridgefield.brr.security.entity.Authority;
import fr.bridgefield.brr.security.entity.Principal;
import fr.bridgefield.brr.security.utilities.JwtUtils;
import jakarta.transaction.Transactional;
@ComponentScan(basePackages={"fr.bridgefield.security","fr.bridgefield.security.dao"})
@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
		/**
		 * 
		 */
		private static final long serialVersionUID = -7448537384463447502L;


	    @Autowired
	    private ApplicationContext applicationContext;
		
		@Autowired 
		PasswordEncoder encoder;
		
		@Autowired
		AuthenticationProvider authProvider;
		
		@Autowired
		SecurityRepository securityPepository;
		
		@Autowired
		JwtUtils jwtUtils;

		String password = "G8w5k5gy%";
		
  @Bean
  CommandLineRunner initDatabase(EmployeeRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
      log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
    };
  }

  @Bean
  CommandLineRunner initUsers(@Autowired SecurityRepository repository) {
	 System.out.println(applicationContext);
	  Authority admin = new Authority("ROLE_ADMIN");
	  try {
		admin = repository.save(admin);
	} catch (SecurityException e) {
	}
	
	Set<Authority> roles = new HashSet<Authority>(Arrays.asList(admin));
	PasswordEncoder encoder = new BCryptPasswordEncoder();
	
	Principal principal = repository.savePrincipal("jlacter", encoder.encode(password));
					
	repository.setRole(principal, roles);

	
	return args -> {
		log.info("Preloading " + principal);
    };
  }

  @Bean
  @Transactional
  CommandLineRunner test() {
	  
	  return ( args -> {
		try {
		  Authentication a = new UsernamePasswordAuthenticationToken("jlacter", password);
		  UserDetails userDetails = securityPepository.loadUserByUsername("jlacter");
		  authProvider.authenticate(a );
			
			
			String token = jwtUtils.generateToken(userDetails.getUsername());
			System.out.println("Generated token : "+token);
		}catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	  
  });
  
  
  }
 }