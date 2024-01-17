package fr.bridgefield.brr.security.entity;



public class SecurityException extends Exception {

	public SecurityException() {
		super();
	}
	
	public static final String FR_BRIDGEFIELD_BADCREDENTIALS = "fr.bridgefield.badcredentials";
	public static final String FR_BRIDGEFIELD_PRINCIPAL_NOT_FOUND = "fr.bridgefield.principalnotfound";
	public static final String FR_BRIDGEFIELD_PRINCIPAL_NO_MAIL = "fr.bridgefield.principalnomail";
	public static final String FR_BRIDGEFIELD_LOCKED_PRINCIPAL = "fr.bridgefield.lockedprincipal";
	public static final String FR_BRIDGEFIELD_PRINCIPAL_NO_CITY = "fr.bridgefield.nocity";
	
}
