package com.ofbusiness.exceptions;

public class ChatlogNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;

	public ChatlogNotFoundException(Long msgId, String userId) {
		super(String.format("Message id = %x not found for user = %s", msgId, userId));
	}
}
