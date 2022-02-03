package com.ofbusiness.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ofbusiness.dtos.ChatlogRequest;
import com.ofbusiness.exceptions.ChatlogNotFoundException;
import com.ofbusiness.exceptions.UserNotFoundException;
import com.ofbusiness.models.Chatlog;
import com.ofbusiness.services.ChatlogService;

@RequestMapping("/chatlogs")
@Controller
public class ChatlogController {
	
	@Autowired
	private ChatlogService chatlogService;

	@PostMapping("/{userId}")
	public ResponseEntity<Long> createChatlog(@PathVariable String userId, @RequestBody ChatlogRequest chatlogRequest){
		Chatlog chatlog = chatlogService.createChatlogEntry(userId, chatlogRequest);
		return new ResponseEntity<>(chatlog.getMsgId(), HttpStatus.CREATED);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<Chatlog>> getChatLogsByPagenation(@PathVariable String userId, @RequestParam(defaultValue = "0") Integer start, @RequestParam(defaultValue = "10") Integer limit) throws UserNotFoundException{
		List<Chatlog> chatlogs = chatlogService.fetchAllChatlogsOfUserByPagenation(userId, start, limit);
		return ResponseEntity.ok().body(chatlogs);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteAllChatlogs(@PathVariable String userId) throws UserNotFoundException{
		chatlogService.deleteAllChatlogsForUser(userId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{userId}/{msgId}")
	public ResponseEntity<Void> deleteChatlogForUser(@PathVariable String userId, @PathVariable Long msgId) throws ChatlogNotFoundException, UserNotFoundException{
		chatlogService.deleteSpecificChatlogOfUser(userId, msgId);
		return ResponseEntity.noContent().build();
	}
}
