package com.ofbusiness.dtos;

import lombok.Data;

@Data
public class ChatlogRequest {
	private String message;
	private Long timestamp;
	private Boolean isSent;
}
