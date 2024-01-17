package fr.bridgefield.brr;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.bridgefield.brr.dao.CityRepository;
import fr.bridgefield.brr.dao.entity.City;
import fr.bridgefield.brr.dao.entity.Photo;
import fr.bridgefield.brr.services.mail.MailService;
import fr.bridgefield.brr.security.entity.Principal;

@SpringBootApplication()

public class Test implements CommandLineRunner{

    private ApplicationContext applicationContext;

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
    Test(){
    	
    }
    

    String secret="";
    
    @Autowired
    CityRepository cityRepository;
    
    @Autowired
    MailService mailService;
    
    public void run(String... args) {
		// TODO Auto-generated method stub
/*		  String imageBase="C:/Users/jerom/brr/img";
		  String s;
		  ObjectMapper om = new ObjectMapper();
		  
		  System.out.println(getApplicationContext());
		  try {
			  s = Files.readString(Paths.get(imageBase+"/user.json"), Charset.defaultCharset());
			  System.out.println("READ FILE ----------------------------");
			  System.out.println(s);
			  System.out.println("END FILE");
			  System.out.println("JSON Object ----------------------------");
			  Principal p = om.readValue(s, Principal.class);
			  System.out.println(p);
			  System.out.println("END JSON");
		  
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  
		}
		 List<City> list = cityRepository.findFirst10ByZipStartsWith("31");
		 for (City c : list){
			 System.out.println(c);
		 }*/
    	
    	//mailService.sendSimpleMessage("jerome.ponchant@laposte.net", "SUBJECT", "Bridgefield");
    	System.out.println("Parameter value : "+secret);
    }
	
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Test.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
		}
		
		
	

}
