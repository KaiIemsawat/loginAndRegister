package com.mvc.registration.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


//Do not need @Entity or @Table since we don't save any information in this process
public class LogUser {

	@NotBlank(message="Email is required!")
    @Email(message="Please enter a valid email!")
    private String logEmail;
    
    @NotBlank(message="Password is required!")
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    private String logPassword;
    
    
    public LogUser() {}


	public String getLogEmail() {
		return logEmail;
	}


	public void setLogEmail(String logEmail) {
		this.logEmail = logEmail;
	}


	public String getLogPassword() {
		return logPassword;
	}


	public void setLogPassword(String logPassword) {
		this.logPassword = logPassword;
	}






    
}
