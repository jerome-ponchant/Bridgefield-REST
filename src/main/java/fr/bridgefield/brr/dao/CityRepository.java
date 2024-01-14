package fr.bridgefield.brr.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.bridgefield.brr.dao.entity.City;

public interface CityRepository extends JpaRepository<City, Long> {

	public City findByZip(String zip);
	
	public List<City> findFirst10ByZipStartsWith(String zip);
	
	public List<City> findFirst10ByName2StartsWith(String name2);
	
	public List<City> findByZipAndName2(String zip, String name2);
	
	public List<City> findByNameLikeOrderByIdDesc(String name);
}
