package fr.bridgefield.brr.dao.entity;

import java.sql.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import fr.bridgefield.brr.security.entity.Authority;
import fr.bridgefield.brr.security.entity.Principal;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;


@Entity
@DiscriminatorValue("USER")
public class User extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7687735538279516011L;
	
	String eMail;
	@OneToOne
	Address address;
	String firstName;
	String lastName;
	String pseudo;
	Locale preferredLocale;
	Date dateOfBirth;
	
	public User() {
		super();
	}
	
	public User(String username, Set<Authority> roles, boolean isAccountNonExpired,
			boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled, String eMail,
			Address address, String firstName, String lastName, String pseudo, Locale preferredLocale,
			Date dateOfBirth) {
		super(username, "", roles, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);

		this.eMail = eMail;
		this.address = address;
		this.firstName = firstName;
		this.lastName = lastName;
		this.pseudo = pseudo;
		this.preferredLocale = preferredLocale;
		this.dateOfBirth = dateOfBirth;
	}
	
	@Override
	public String toString() {
		return "User [eMail=" + eMail + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public Locale getPreferredLocale() {
		return preferredLocale;
	}

	public void setPreferredLocale(Locale preferredLocale) {
		this.preferredLocale = preferredLocale;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(address, dateOfBirth, eMail, firstName, lastName, preferredLocale, pseudo);
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
		User other = (User) obj;
		return Objects.equals(address, other.address) && Objects.equals(dateOfBirth, other.dateOfBirth)
				&& Objects.equals(eMail, other.eMail) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(preferredLocale, other.preferredLocale)
				&& Objects.equals(pseudo, other.pseudo);
	}

}
