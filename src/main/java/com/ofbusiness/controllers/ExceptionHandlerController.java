package com.ofbusiness.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.ofbusiness.exceptions.ChatlogNotFoundException;
import com.ofbusiness.exceptions.UserNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public Map<String, String> handleUserNotFoundException(UserNotFoundException ex, WebRequest req){
		return generateGeneralResponse(ex.getMessage());
	}
	
	@ExceptionHandler(ChatlogNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public Map<String, String> handleChatlogNotFoundException(ChatlogNotFoundException ex, WebRequest req){
		return generateGeneralResponse(ex.getMessage());
	}
	
	private Map<String, String> generateGeneralResponse(String message){
		Map<String, String> response = new HashMap<>();
		response.put("message", message);
		response.put("time", new Date().toString());
		return response;
	}
}
