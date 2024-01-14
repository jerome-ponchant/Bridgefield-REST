package fr.bridgefield.brr.security.entity;



import java.util.Objects;

import org.springframework.security.authentication.BadCredentialsException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Authority implements org.springframework.security.core.GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7668844408931173991L;
	
	public final static String ROLE_TENANT_NAME = "ROLE_TENANT";

	@Id
	@GeneratedValue
	Long id;
	
	String name;
	
	
	
	public Authority(String authority) {
		super();
		this.name = authority;
	}

	Authority() {
		super();
	}

	
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	Long getId() {
		return id;
	}

	void setId(Long id) {
		this.id = id;
	}

	void setName(String authority) {
		this.name = authority;
	}

	@Override
	public String toString() {
		return "GrantedAuthority [id=" + id + ", authority=" + name + "]";
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		BadCredentialsException e;
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Authority other = (Authority) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

}
