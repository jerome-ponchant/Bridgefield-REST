package fr.bridgefield.brr.dao.entity;

import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import fr.bridgefield.brr.security.entity.Authority;
import fr.bridgefield.brr.security.entity.Principal;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Embedded;

@Entity
@DiscriminatorValue("ORGANIZATION")
public class Organization extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3458849736622336917L;

	public static final String PRINCIPAL_TYPE="ORGANIZATION" ;
	
	String name;
	String code;
	String contactMail;
	String phoneNumber;
	@Embedded
	Address address;
	Locale preferredLocale;

	@OneToOne
	Photo logo;

	public Organization() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Organization(String username, String password, Set<Authority> roles, boolean isAccountNonExpired,
			boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
		super(username, password, roles, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled, "ORGANIZATION");
		// TODO Auto-generated constructor stub
	}

	public Organization(String username, String password, Set<Authority> roles, boolean isAccountNonExpired,
			boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled, String name, String code,
			String contactMail, String phoneNumber, Address address, Locale preferredLocale, Photo logo) {
		super(username, password, roles, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled, "ORGANIZATION");
		this.name = name;
		this.code = code;
		this.contactMail = contactMail;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.preferredLocale = preferredLocale;
		this.logo = logo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(code, contactMail, name);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Organization other = (Organization) obj;
		return Objects.equals(code, other.code) && Objects.equals(contactMail, other.contactMail)
				&& Objects.equals(name, other.name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContactMail() {
		return contactMail;
	}

	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Locale getPreferredLocale() {
		return preferredLocale;
	}

	public void setPreferredLocale(Locale preferredLocale) {
		this.preferredLocale = preferredLocale;
	}

	public Photo getLogo() {
		return logo;
	}

	public void setLogo(Photo logo) {
		this.logo = logo;
	}

	
}
