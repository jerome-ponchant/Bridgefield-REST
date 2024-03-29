package fr.bridgefield.brr.security.entity;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import fr.bridgefield.brr.dao.entity.Organization;
import fr.bridgefield.brr.dao.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.InheritanceType;


@JsonTypeInfo(
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "principalType",
        use = JsonTypeInfo.Id.NAME,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = User.class, name = "USER"),
        @JsonSubTypes.Type(value = Organization.class, name = "ORGANIZATION")
})





@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="principalType")
@DiscriminatorValue("PRINCIPAL")
public class Principal implements UserDetails {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1289471056417133798L;
	public static final String GUEST_USER_NAME = "guest_user";
	public static final String PRINCIPAL_TYPE="PRINCIPAL" ;
	
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
	
	@Column(insertable=false, updatable=false)
	protected String principalType;
	
	public Principal(String username, String password, Set<Authority> roles,
			boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired,
			boolean isEnabled, String principalType) {
		super();
		this.username = username;
		this.password = password;
		this.authorities =  roles;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
		this.principalType=principalType;
	}

	public Principal() {
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

	protected void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getPrincipalType() {
		return principalType;
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
