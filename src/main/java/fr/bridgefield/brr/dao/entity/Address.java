package fr.bridgefield.brr.dao.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Address {

	
	
	@Id
	@GeneratedValue
	Long id;
	

	String address1;
	String address2;
	String zip;
	String city;
	String state;
	String country;
	
	public Address() {}
	
	
	public Address(String address1, String address2, String zip, String city, String state, String country) {
		super();
		this.address1 = address1;
		this.address2 = address2;
		this.zip = zip;
		this.city = city;
		this.state = state;
		this.country = country;
	}	
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(address1, address2, city, country, id, state, zip);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(address1, other.address1) && Objects.equals(address2, other.address2)
				&& Objects.equals(city, other.city) && Objects.equals(country, other.country)
				&& Objects.equals(id, other.id) && Objects.equals(state, other.state) && Objects.equals(zip, other.zip);
	}
	
	Long getId() {
		return id;
	}
	void setId(Long id) {
		this.id = id;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", address1=" + address1 + ", address2=" + address2 + ", zip=" + zip + ", city="
				+ city + ", state=" + state + ", country=" + country + "]";
	}

	
}
