package org.example.risabackend;

import org.example.risabackend.models.User;
import org.example.risabackend.repositories.NotificationRepository;
import org.example.risabackend.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class RisaBackendApplication {

	private static final Logger log = LoggerFactory.getLogger(RisaBackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RisaBackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepository, NotificationRepository notificationRepository) {
		return args -> {

			User user1 = new User("Soreti Ne", "soreti.ne@gmail.com", "416-788-5313", "profile pic", "default");
			User user2 = new User("Water Bottle", "water.bottle@gmail.com", "416-123-1223", "profile pic", "default");

			// curl -X POST localhost:8080/user -H 'Content-type:application/json' -d '{"fullName": "New User", "email": "new.user@gmail.com", "phoneNumber": "514-113-0922", "profilePicture": "profile pic", "status": "default"}'
			// curl -X POST localhost:8080/ -H 'Content-type:application/json' -d '{"name": "Samwise Gamgee", "role": "gardener"}'

			// curl -X PUT localhost:8080/user -H 'Content-type:application/json' -d '{"fullName": "New User", "email": "new.user@gmail.com", "phoneNumber": "514-113-0922", "profilePicture": "profile pic", "status": "default"}'
			// curl -X PUT localhost:8080/user -H 'Content-type:application/json' -d '{"name": "Samwise Gamgee", "role": "ring bearer"}'

			// save a few users
			userRepository.save(user1);
			userRepository.save(user2);

//			log.info("Users found");
//			log.info("---------------------------------------");
//			userRepository.findAll().forEach(user -> {
//				log.info(user.toString());
//				log.info("");
//			});
		};
	}
}