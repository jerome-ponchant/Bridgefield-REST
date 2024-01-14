package fr.bridgefield.brr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication()
@CrossOrigin(origins = {"http://localhost:4200", "https://www.bridgefield.fr", "https://bridgefield.fr"})
@RestController
@RequestMapping("/brr")
public class StartApplication {

	public StartApplication() {
		
	}

	public static void main(String[] args) {
		
		SpringApplication.run(StartApplication.class, args);
	}
    
	@GetMapping("/hello")
    String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
      return String.format("Hello Magnificient %s!", name);
    }	



    

}
