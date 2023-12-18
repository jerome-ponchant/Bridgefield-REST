package fr.bridgefield.brr.security;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.bridgefield.brr.security.entity.Exception;
import fr.bridgefield.brr.security.entity.SecurityException;

@ControllerAdvice
public class SecurityAdvice {

	@ResponseBody()
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	@ExceptionHandler(SecurityException.class)
	String handle(SecurityException e) {
		return new JSONObject(e).toString();		
	}
	
}
