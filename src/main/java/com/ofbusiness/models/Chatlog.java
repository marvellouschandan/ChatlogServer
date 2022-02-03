package com.ofbusiness.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ofbusiness.dtos.ChatlogRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chatlog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long msgId;
	private String message;
	private Long timestamp;
	private Boolean isSent;
	@JsonBackReference
	@ManyToOne(targetEntity = User.class)
	private User user;
	
	public Chatlog(ChatlogRequest chatlogRequest){
		this.message = chatlogRequest.getMessage();
		this.timestamp = chatlogRequest.getTimestamp();
		this.isSent = chatlogRequest.getIsSent();
	}
}
