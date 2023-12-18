package fr.bridgefield.brr.security.entity;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Principal implements UserDetails {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1289471056417133798L;

	@Id
	@GeneratedValue()
	Long id;

	
	private String username;	
	private String password; 
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Authority> authorities;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;
	private boolean isEnabled;
	
	public Principal(String username, String password, Set<Authority> roles,
			boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired,
			boolean isEnabled) {
		super();
		this.username = username;
		this.password = password;
		this.authorities =  roles;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
	}

	Principal() {
		super();
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isEnabled;
	}


	public Long getId() {
		return id;
	}

	void setId(Long id) {
		this.id = id;
	}

	void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Override
	public int hashCode() {
		return Objects.hash( id, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired,
				isEnabled, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Principal other = (Principal) obj;
		return Objects.equals(id, other.id)
				&& isAccountNonExpired == other.isAccountNonExpired && isAccountNonLocked == other.isAccountNonLocked
				&& isCredentialsNonExpired == other.isCredentialsNonExpired && isEnabled == other.isEnabled
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

}
