package fr.bridgefield.brr.security.entity;

public class SuccessResponse {

	boolean success=false;

	public SuccessResponse(boolean success) {
		super();
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}
