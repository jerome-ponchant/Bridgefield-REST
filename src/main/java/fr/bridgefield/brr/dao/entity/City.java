package fr.bridgefield.brr.dao.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class City {

	@Id
	Long id;
	
	@Column(length = 10)
	String code;
	
	String name;
	String name2;

	@Column(length = 10)
	String zip;
	
	String country;

	public City() {
		super();
		// TODO Auto-generated constructor stub
	}

	public City(String code, String name, String name2, String zip, String country) {
		super();
		this.code = code;
		this.name = name;
		this.name2 = name2;
		this.zip = zip;
		this.country = country;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, country, id, name, name2, zip);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		return Objects.equals(code, other.code) && Objects.equals(country, other.country)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(name2, other.name2) && Objects.equals(zip, other.zip);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
