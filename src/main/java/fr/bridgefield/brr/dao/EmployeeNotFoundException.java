package fr.bridgefield.brr.dao;


public class EmployeeNotFoundException extends RuntimeException {

  /**
	 * 
	 */
	private static final long serialVersionUID = -6313458592626229939L;

public EmployeeNotFoundException(Long id) {
    super("Could not find employee " + id);
  }
}
