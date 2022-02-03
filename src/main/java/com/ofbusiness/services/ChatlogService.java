package com.ofbusiness.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ofbusiness.dtos.ChatlogRequest;
import com.ofbusiness.exceptions.ChatlogNotFoundException;
import com.ofbusiness.exceptions.UserNotFoundException;
import com.ofbusiness.models.Chatlog;
import com.ofbusiness.models.User;
import com.ofbusiness.repositories.ChatlogRepository;

@Service
public class ChatlogService {
	
	@Autowired
	private ChatlogRepository chatlogRepository;
	@Autowired
	private UserService userService;
	private final String sortingField = "timestamp";
	
	public Chatlog createChatlogEntry(String userId, ChatlogRequest chatlogRequest) {
		Chatlog chatlog = new Chatlog(chatlogRequest);
		chatlog.setUser(new User(userId));
		return chatlogRepository.save(chatlog);
	}

	public List<Chatlog> fetchAllChatlogsOfUserByPagenation(String userId, Integer start, Integer limit) throws UserNotFoundException {
		userService.isUserExists(userId);
		Pageable pageSortByField = PageRequest.of(start, limit, Sort.by(sortingField).descending());
		return chatlogRepository.findAllByUser(new User(userId), pageSortByField);
	}
	
	public void deleteAllChatlogsForUser(String userId) throws UserNotFoundException {
		userService.isUserExists(userId);
		userService.deleteUser(userId);
		userService.createUser(new User(userId, new ArrayList<Chatlog>()));
	}

	public void deleteSpecificChatlogOfUser(String userId, Long msgId) throws ChatlogNotFoundException, UserNotFoundException {
		userService.isUserExists(userId);
		Optional<Chatlog> chatlog = chatlogRepository.findById(msgId);
		if (chatlog.isPresent() && chatlog.get().getUser().getId() == userId) {
			chatlogRepository.deleteById(msgId);
		}else {
			throw new ChatlogNotFoundException(msgId, userId);
		}
	}
	
	
}
