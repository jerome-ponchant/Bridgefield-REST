package fr.bridgefield.brr.security.entity;



public class SecurityException extends Exception {

	public SecurityException() {
		super();
	}
	
	public static final String FR_BRIDGEFIELD_BADCREDENTIALS = "fr.bridgefield.badcredentials";
	public static final String FR_BRIDGEFIELD_PRINCIPAL_NOT_FOUND = "fr.bridgefield.principalnotfound";
}
