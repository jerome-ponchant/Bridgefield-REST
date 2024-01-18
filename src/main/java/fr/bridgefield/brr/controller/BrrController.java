package fr.bridgefield.brr.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.bridgefield.brr.dao.CityRepository;
import fr.bridgefield.brr.dao.PhotoRepository;
import fr.bridgefield.brr.dao.entity.City;
import fr.bridgefield.brr.dao.entity.Photo;
import fr.bridgefield.brr.security.entity.AuthenticationRequest;
import fr.bridgefield.brr.security.entity.SecurityException;
import fr.bridgefield.brr.security.entity.SuccessResponse;

@CrossOrigin(origins = {"http://localhost:4200", "https://www.bridgefield.fr", "https://bridgefield.fr"})
@RestController
@RequestMapping("/brr")
public class BrrController {

	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private PhotoRepository photoRepository;

	@CrossOrigin
	@GetMapping("/public/cities/zip/startsWith/{startZip}")
	ResponseEntity<List<City>> CitiesFirstZip(@PathVariable("startZip") String body) throws SecurityException{
		ResponseEntity<List<City>> response = new ResponseEntity<List<City>>(cityRepository.findFirst10ByZipStartsWith(body),HttpStatus.OK);
		
		return response;
	}

	
	@CrossOrigin
	@GetMapping("/public/cities/findByZipAndName2/")
	ResponseEntity<List<City>> findByZipAndName2(@RequestParam(name = "zip") String zip, @RequestParam(name = "name2") String name2) throws SecurityException{
		ResponseEntity<List<City>> response;
		List<City> listZip = new ArrayList<City>();
		if( zip !="" ) listZip= cityRepository.findByZipStartingWith(zip);
		
		List<City> listName2 = new ArrayList<City>();
		if(name2!="" )listName2 = cityRepository.findByName2StartingWith(name2);
		
		response = new ResponseEntity<List<City>>(intersection(listZip, listName2), HttpStatus.FOUND); 
		
		return response;
	}
	
	@CrossOrigin
	@GetMapping("/public/cities/findAll/")
	ResponseEntity<List<City>> findAll(){
		ResponseEntity<List<City>> response;
		List<City> list = new ArrayList<City>();
		list = cityRepository.findByIdGreaterThanOrderByZip(-1);
		
		response = new ResponseEntity<List<City>>(list,HttpStatus.OK);
		
		return response;
	}
	
	@CrossOrigin
	@GetMapping("/public/cities/name2/startsWith/{startName}")
	ResponseEntity<List<City>> CitiesFirstName(@PathVariable("startName") String body) throws SecurityException{
		ResponseEntity<List<City>> response = new ResponseEntity<List<City>>(cityRepository.findFirst10ByName2StartsWith(body),HttpStatus.OK);
		
		return response;
	}

	@CrossOrigin
	@GetMapping("/public/photos/id/{id}")
	ResponseEntity<Photo> photoById(@PathVariable("id") Long id) throws Exception{
		ResponseEntity<Photo> response = new ResponseEntity<Photo>(photoRepository.findById(id).get(),HttpStatus.OK);
		return response;
	}
	
	@CrossOrigin
	@PostMapping("/public/photos")
	ResponseEntity<Photo> savePhoto(@RequestBody Photo photo){
		Photo response = photoRepository.save(photo);
		return new ResponseEntity<Photo>(response,HttpStatus.OK);
	
	}
	
	private <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();
        if(list1.isEmpty())return list2;
        if(list2.isEmpty()) return list1;
        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }
	
}
