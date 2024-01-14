package fr.bridgefield.brr.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.bridgefield.brr.security.dao.AuthorityRepository;
import fr.bridgefield.brr.security.dao.PrincipalRepository;
import fr.bridgefield.brr.security.entity.Authority;
import fr.bridgefield.brr.security.entity.Principal;

@Configuration
@Service
public class SecurityRepository implements UserDetailsService {

	public SecurityRepository() {
		super();
	}

	@Autowired
	PrincipalRepository principalRepository;

	@Autowired
	AuthorityRepository authorityRepository;

	public class SecurityException extends RuntimeException {

		public SecurityException(String string) {
			super(string);
		}
	};

	public Principal savePrincipal(String username, String encryptedPassword) throws SecurityException {
		Principal p = new Principal(username, encryptedPassword, null, true, true, true, true, null);
		List<Principal> l = principalRepository.findByUsername(username);
		if (l == null || l.size() != 0) {
			p = l.get(0);
			p.setPassword(encryptedPassword);
		}

		p = principalRepository.save(p);
		principalRepository.flush();
		return p;
	}

	public Principal setRole(Principal principal, Set<Authority> authorities) throws SecurityException {
		Principal p = principalRepository.findById(principal.getId()).orElseThrow(() -> {
			throw new SecurityException("User " + principal.getUsername() + " not found.");
		});
		Set<Authority> s = new HashSet<Authority>();
		List<Authority> list = authorityRepository.findAll();
		for (Authority a : authorities) {
			int i = list.indexOf(a);
			if (i == -1) {
				throw new SecurityException("Role " + a.getName() + " not found.");
			}
			a = list.get(i);
			s.add(a);
		}
		p.setAuthorities(s);
		principalRepository.save(p);
		principalRepository.flush();
		return p;
	}

	public Authority save(Authority a) throws SecurityException {
		List<Authority> list = authorityRepository.findByName(a.getName());
		if (list == null || (list != null && list.size() > 0)) {
			return list.get(0);
		}
		Authority b = authorityRepository.save(a);
		authorityRepository.flush();
		return b;

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		List<Principal> l = principalRepository.findByUsername(username);
		try {
			return (l.get(0));
		} catch (Exception e) {
			throw new UsernameNotFoundException("User " + username + " not found.");
		}
	}

}
