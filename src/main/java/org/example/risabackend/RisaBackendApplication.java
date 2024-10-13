package org.example.risabackend;

import org.example.risabackend.user.UserResponseDto;
import org.example.risabackend.user.User;
import org.example.risabackend.user.UserService;
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
	public CommandLineRunner commandLineRunner(UserService userService) {
		return args -> {

			List<UserResponseDto> allUsers = userService.getAllUsers();

			if (allUsers.isEmpty()) {
				User user1 = new User("Emily Grant", "555-123-1234", "test123");
				User user2 = new User("Ketchup Kid", "098-098-0987", "test123");
				User user3 = new User("Jane Doe", "345-345-3456", "test123");
				User user4 = new User("John Doe",  "345-345-3457", "test123");
				User user5 = new User("Gordon Ramsey", "930-992-3211", "test123");
				User user6 = new User("Jamie Oliver", "930-381-1754", "test123");


				userService.createUser(user1);
				userService.createUser(user2);
				userService.createUser(user3);
				userService.createUser(user4);

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
			}
		};
	}
}


/*
 * curl -X GET localhost:8080/user/userNotifications/852
 * curl -X POST localhost:8080/user/register -H 'Content-type:application/json' -d '{"fullName": "New User", "password": "password123", "phoneNumber": "514-113-0922"}'
 * curl -X PUT localhost:8080/user/3 -H 'Content-type:application/json' -d '{"fullName": "Bleep Bleep", "password": "password123", "email": "bleep.bleep@gmail.com", "phoneNumber": "514-113-0922"}'
 * curl -X DELETE localhost:8080/user/0
 * */

