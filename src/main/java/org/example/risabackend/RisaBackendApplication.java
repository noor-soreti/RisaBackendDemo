package org.example.risabackend;

import org.example.risabackend.persistence.entity.ChatLog;
import org.example.risabackend.persistence.entity.User;
import org.example.risabackend.persistence.repositories.ChatLogRepository;
import org.example.risabackend.persistence.repositories.MessageRepository;
import org.example.risabackend.persistence.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class RisaBackendApplication {

	private static final Logger log = LoggerFactory.getLogger(RisaBackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RisaBackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepository, ChatLogRepository chatLogRepository, MessageRepository messageRepository) {
		return args -> {

			List<User> allUsers = userRepository.findAll();

			if (allUsers.isEmpty()) {
//				User user1 = new User("Emily Grant", "emily.grant@gmail.com", "123-123-1234");
//				User user2 = new User("Ketchup Kid", "ketchup.kid@gmail.com", "098-098-0987");
//				User user3 = new User("Jane Doe", "jane.doe@gmail.com", "345-345-3456");
//
//				userRepository.save(user1);
//				userRepository.save(user2);
//				userRepository.save(user3);
//
//
//
//				// create or find chatLog
//				ChatLog chatLog = new ChatLog(new HashSet<>(), new HashSet<>());
//				chatLog.getUsers().add(user1);
//				chatLog.getUsers().add(user2);
//				chatLogRepository.save(chatLog);
//
//				// update user instances to reflect chatLog
//
//				user1.getChatLogs().add(chatLog);
//				user2.getChatLogs().add(chatLog);
//
//				userRepository.save(user1);
//				userRepository.save(user2);
//
//				// create message
//
//				Message message = new Message(user1.getUserId(), "Hello, " + user2.getFullName(), System.currentTimeMillis());
//				messageRepository.save(message);
//				chatLog.getMessages().add(message);
//				chatLogRepository.save(chatLog);


				// ------------------------------------------------------------------
			}
		};
	}
}


/*
 * curl -X GET localhost:8080/user/userNotifications/852
 * curl -X POST localhost:8080/user/register -H 'Content-type:application/json' -d '{"fullName": "New User", "password": "password123", "email": "new.user@gmail.com", "phoneNumber": "514-113-0922"}'
 * curl -X PUT localhost:8080/user/3 -H 'Content-type:application/json' -d '{"fullName": "Bleep Bleep", "password": "password123", "email": "bleep.bleep@gmail.com", "phoneNumber": "514-113-0922"}'
 * curl -X DELETE localhost:8080/user/0
 * */

