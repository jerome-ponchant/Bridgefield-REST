package fr.bridgefield.brr.controller;

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
	@GetMapping("/cities/zip/startsWith/{startZip}")
	ResponseEntity<List<City>> CitiesFirstZip(@PathVariable("startZip") String body) throws SecurityException{
		ResponseEntity<List<City>> response = new ResponseEntity<List<City>>(cityRepository.findFirst10ByZipStartsWith(body),HttpStatus.OK);
		
		return response;
	}

	@CrossOrigin
	@GetMapping("/cities/name2/startsWith/{startName}")
	ResponseEntity<List<City>> CitiesFirstName(@PathVariable("startName") String body) throws SecurityException{
		ResponseEntity<List<City>> response = new ResponseEntity<List<City>>(cityRepository.findFirst10ByName2StartsWith(body),HttpStatus.OK);
		
		return response;
	}

	@CrossOrigin
	@GetMapping("/sandbox/photos/id/{id}")
	ResponseEntity<Photo> photoById(@PathVariable("id") Long id) throws Exception{
		ResponseEntity<Photo> response = new ResponseEntity<Photo>(photoRepository.findById(id).get(),HttpStatus.OK);
		return response;
	}
	
	@CrossOrigin
	@PostMapping("/sandbox/photos")
	ResponseEntity<Photo> savePhoto(@RequestBody Photo photo){
		Photo response = photoRepository.save(photo);
		return new ResponseEntity<Photo>(response,HttpStatus.OK);
	
	}
}
