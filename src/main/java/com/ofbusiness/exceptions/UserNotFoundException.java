package com.ofbusiness.exceptions;

public class UserNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String userId) {
		super(String.format("User not found : %s", userId));
	}
}
