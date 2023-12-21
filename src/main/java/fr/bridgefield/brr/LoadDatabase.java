package fr.bridgefield.brr;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
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

import fr.bridgefield.brr.dao.AddressRepository;
import fr.bridgefield.brr.dao.UserRepository;
import fr.bridgefield.brr.dao.entity.Address;
import fr.bridgefield.brr.dao.entity.User;
import fr.bridgefield.brr.security.SecurityRepository;
import fr.bridgefield.brr.security.SecurityRepository.SecurityException;
import fr.bridgefield.brr.security.dao.AuthorityRepository;
import fr.bridgefield.brr.security.dao.PrincipalRepository;
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
		
		@Autowired
		UserRepository userRepository;
		
		@Autowired
		AddressRepository addressRepository;

		String password = "G8w5k5gy%";
		

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
	
	Date dob = Date.valueOf("1974-07-30");
	Address address = new Address();
	address.setAddress1("40, rue des quêteurs");
	address.setCity("Toulouse");
	address.setZip("31000");
	
	address = addressRepository.save(address);
	
	User user = new User("jponchant",roles, false, false, false, false, "jerome.ponchant@laposte.net", 
			address, "Jérôme", "Ponchant", "", Locale.FRANCE, dob) ;
	
	userRepository.save(user);
			
	Principal principal = repository.savePrincipal("jponchant", encoder.encode(password));
					
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