package fr.bridgefield.brr.dao.entity;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Embeddable
public class Address {

	String address1;
	String address2;
	
	@ManyToOne
	City city;
	
	public Address() {}
	
	
	public Address(String address1, String address2, City city ) {
		super();
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
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


	public City getCity() {
		return city;
	}


	public void setCity(City city) {
		this.city = city;
	}


	@Override
	public int hashCode() {
		return Objects.hash(address1, address2, city);
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
				&& Objects.equals(city, other.city);
	}


	@Override
	public String toString() {
		return "Address [address1=" + address1 + ", address2=" + address2 + ", city=" + city + "]";
	}

	
	
}
