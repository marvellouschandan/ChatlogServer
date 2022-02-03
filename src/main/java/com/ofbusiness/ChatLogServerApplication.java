package com.ofbusiness;

import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ofbusiness.models.Chatlog;
import com.ofbusiness.models.User;
import com.ofbusiness.repositories.ChatlogRepository;
import com.ofbusiness.repositories.UserRepository;

@SpringBootApplication
public class ChatLogServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatLogServerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserRepository userRepository, ChatlogRepository chatlogRepository) {
		return args -> {
			User user1 = new User("CHANDAN170");
			User user2 = new User("BHUVANG125");
			
			Random rand = new Random(); 
			int min=200000, max=1000000;
			
			userRepository.save(user1);
			userRepository.save(user2);
			
			for (int i=1; i<=20; i++) {
				Long timestamp = Long.valueOf(rand.nextInt((max - min) + 1) + min);
				Chatlog chatlog = new Chatlog(null, String.format("Sample message %d for Chandan", i), timestamp, true, user1);
				chatlogRepository.save(chatlog);
			}
			
			Chatlog chatlog2 = new Chatlog(null, "Sample message for Bhuvan", 123456L, true, user2);
			chatlogRepository.save(chatlog2);
		};
	}
}
