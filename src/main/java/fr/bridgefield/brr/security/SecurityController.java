package fr.bridgefield.brr.security;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.bridgefield.brr.dao.CityRepository;
import fr.bridgefield.brr.dao.PhotoRepository;
import fr.bridgefield.brr.dao.entity.Address;
import fr.bridgefield.brr.dao.entity.City;
import fr.bridgefield.brr.dao.entity.Organization;
import fr.bridgefield.brr.dao.entity.Photo;
import fr.bridgefield.brr.dao.entity.User;
import fr.bridgefield.brr.services.mail.MailService;
import fr.bridgefield.brr.security.dao.AuthorityRepository;
import fr.bridgefield.brr.security.dao.PrincipalRepository;
import fr.bridgefield.brr.security.entity.AuthenticationRequest;
import fr.bridgefield.brr.security.entity.AuthenticationResponse;
import fr.bridgefield.brr.security.entity.Authority;
import fr.bridgefield.brr.security.entity.Principal;
import fr.bridgefield.brr.security.entity.SecurityException;
import fr.bridgefield.brr.security.entity.SuccessResponse;
import fr.bridgefield.brr.security.utilities.JwtUtils;

@CrossOrigin(origins = {"http://localhost:4200", "https://www.bridgefield.fr", "https://bridgefield.fr"})
@RestController
@RequestMapping("/auth")
public class SecurityController {

	private  static Set<Authority> DEFAULT_AUTHORITIES;

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private SecurityRepository securityPepository;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	PrincipalRepository principalRepository;
	
	@Autowired
	PhotoRepository photoRepository;
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	Set<Authority> getDefaultAuthorities(){
		if (SecurityController.DEFAULT_AUTHORITIES == null) {
			SecurityController.DEFAULT_AUTHORITIES = new HashSet<Authority>(
					authorityRepository.findByName(Authority.ROLE_TENANT_NAME)	
					);
		}
		return SecurityController.DEFAULT_AUTHORITIES;
	}
	
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
		catch(LockedException le) {
			SecurityException securityException = new SecurityException();
			securityException.setLocalizedMessage(SecurityException.FR_BRIDGEFIELD_LOCKED_PRINCIPAL, body.getUserName());
			throw (securityException);
		}
		
		AuthenticationResponse token = new AuthenticationResponse(jwtUtils.generateToken(p.getUsername()), p);
		return ResponseEntity.ok(token);
	}	
	
	@CrossOrigin
	@PostMapping("/register")
	ResponseEntity<?> register(@RequestBody Principal body) throws SecurityException{
		body.setEnabled(false);
		body.setAccountNonExpired(true);
		body.setAccountNonLocked(true);
		body.setCredentialsNonExpired(true);
		body.setAuthorities(getDefaultAuthorities());
		body.setPassword(passwordEncoder.encode(body.getPassword()));
		Photo p;
		String mail="";
		Locale locale=Locale.US;
		
			switch(body.getPrincipalType()) {
				case User.PRINCIPAL_TYPE :
					User u = (User)body;
					p=u.getPhoto();
					if(p!=null) {
						p=photoRepository.save(p);
						u.setPhoto(p);
					}
					updateCity(u.getAddress());
					mail = u.geteMail();
					if( u.getPreferredLocale()== null )
						u.setPreferredLocale(locale);
					else {
						locale=u.getPreferredLocale();
					}
					break;
				case Organization.PRINCIPAL_TYPE :
					Organization o = (Organization)body;
					p = o.getLogo();
					if(p!=null) {
						p = photoRepository.save(p);
						o.setLogo(p);
					}
					updateCity(o.getAddress());
					mail=o.getContactMail();
					if( o.getPreferredLocale()== null )
						o.setPreferredLocale(locale);
					else {
						locale=o.getPreferredLocale();
					}
					break;
			}
			
		
		body = principalRepository.save(body);
		try {
			mailService.sendValidationMail(body,mail,locale);
			}
			catch (MailException e) {
				SecurityException se = new SecurityException();
				se.setLocalizedMessage(SecurityException.FR_BRIDGEFIELD_PRINCIPAL_NO_MAIL);
				throw (se);
			}
		return ResponseEntity.ok(body);
	}
	
	private void updateCity(Address address) {
		
		City c = address.getCity();
		List<City> cities = cityRepository.findByZipAndName2(c.getZip(), c.getName2().toUpperCase());
		
		if(cities.size() > 0)
			c = cities.get(0);
		else{
			cities = cityRepository.findFirst10ByName2StartsWith(c.getName2().toUpperCase());
			if(cities.size() > 0) {
				c = cities.get(0);
			}
			else{
				cities = cityRepository.findFirst10ByZipStartsWith(c.getZip());
				if(cities.size() > 0) {
					c = cities.get(0);
				}
				else {
					City last = cityRepository.findByNameLikeOrderByIdDesc("%").get(0);
					c.setId(last.getId()+1);
					c = cityRepository.save(c);
				}
			}
		}
		address.setCity(c);
	}
		

	@CrossOrigin
	@GetMapping("/principals/validate/{id}")
	ResponseEntity<Principal> validate(@PathVariable("id") Long id){
		Principal p = principalRepository.getReferenceById(id);
		p.setEnabled(true);
		p=principalRepository.save(p);
		return new ResponseEntity<Principal>(HttpStatus.ACCEPTED).ok(p);
	}
}
