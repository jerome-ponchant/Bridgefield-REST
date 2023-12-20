package fr.bridgefield.brr.security.entity;

import java.util.Locale;
import java.util.ResourceBundle;

import jakarta.servlet.ServletException;

public class Exception extends ServletException{
	
	private static final String FR_BRIDGEFIELD_UN_MANAGED_EXCEPTION = "fr.bridgefield.unManagedException";
	/**
	 * 
	 */
	private static final long serialVersionUID = -4755671521130464614L;
	private static final String EXCEPTION_MESSAGES = "exceptionMessages";
	
	
	protected static ResourceBundle messages = ResourceBundle.getBundle(EXCEPTION_MESSAGES);
	
	private String localizedMessage;
	private String key;
	
	public Exception() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Exception(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}



	public Exception(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}



	public Exception(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}



	public String getLocalizedMessage() {
		return localizedMessage;
	}



	public void setLocalizedMessage(String key, Object... args ) {
		// TODO Auto-generated method stub
		this.key = key;
		localizedMessage = Exception.messages.getString(key).formatted(args);
		
		 
	}

	public void setUnhandledMessage(String message) {
		this.key = FR_BRIDGEFIELD_UN_MANAGED_EXCEPTION;
		localizedMessage = message;
		
	}

	public String getKey() {
		return key;
	}



	public void setKey(String key) {
		this.key = key;
	}

}
