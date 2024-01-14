package fr.bridgefield.brr.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Photo {

	@Id
	@GeneratedValue()
	Long id;


	String url;
	
	@Lob
	@Column( columnDefinition="LONGTEXT")
	String data;
	
	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Photo(String url) {
		super();
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Photo [id=" + id + ", url=" + url + "]";
	}



}
