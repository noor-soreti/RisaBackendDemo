package org.example.risabackend.services;

import org.example.risabackend.api.dto.UserResponseDto;
import org.example.risabackend.persistence.entity.ChatLog;
import org.example.risabackend.persistence.entity.Message;
import org.example.risabackend.persistence.entity.User;
import org.example.risabackend.persistence.repositories.ChatLogRepository;
import org.example.risabackend.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private UserRepository userRepository;
    private ChatLogRepository chatLogRepository;

    @Autowired
    public UserService(UserRepository userRepository, ChatLogRepository chatLogRepository) {
        this.userRepository = userRepository;
        this.chatLogRepository = chatLogRepository;
    }

    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userRequestDtos = new ArrayList<>();
        for (User user : users) {
            userRequestDtos.add(
                    UserResponseDto.builder()
                            .id(user.getUserId())
                            .email(user.getEmail())
                            .fullName(user.getFullName())
                            .profilePicture(user.getProfilePicture())
                            .status(user.getStatus())
                            .lastSeen(user.getLastSeen())
                            .isOnline(user.isOnline())
                            .chatLogs(user.getChatLogs())
                            .build()
            );
        }

        return userRequestDtos;

    }

    public UserResponseDto getUseDtoById(Long id) {
        User userEntity = userRepository.findFirstByUserId(id);

        return UserResponseDto.builder()
                .id(userEntity.getUserId())
                .email(userEntity.getEmail())
                .fullName(userEntity.getFullName())
                .profilePicture(userEntity.getProfilePicture())
                .status(userEntity.getStatus())
                .lastSeen(userEntity.getLastSeen())
                .isOnline(userEntity.isOnline())
                .chatLogs(userEntity.getChatLogs())
                .build();
    }

    public UserResponseDto getUserByEmail(String email) {
        User userEntity = userRepository.findByEmail(email);
        return UserResponseDto.builder()
                .id(userEntity.getUserId())
                .email(userEntity.getEmail())
                .fullName(userEntity.getFullName())
                .profilePicture(userEntity.getProfilePicture())
                .status(userEntity.getStatus())
                .lastSeen(userEntity.getLastSeen())
                .isOnline(userEntity.isOnline())
                .chatLogs(userEntity.getChatLogs())
                .build();
    }

    public UserResponseDto createUser(User newUser) {
        User user = userRepository.findByEmail(newUser.getEmail());

        if (user != null) {
            return null;
        }

        userRepository.save(newUser);

        return UserResponseDto.builder()
                .id(newUser.getUserId())
                .email(newUser.getEmail())
                .fullName(newUser.getFullName())
                .profilePicture(newUser.getProfilePicture())
                .status(newUser.getStatus())
                .lastSeen(newUser.getLastSeen())
                .isOnline(newUser.isOnline())
                .chatLogs(newUser.getChatLogs())
                .build();
    }

    public void createNewChatLog(Set<Long> userIds, Long chatLogId) {
        Set<User> userList = new HashSet<>();
        for (Long id : userIds) {
            userRepository.findById(id).ifPresent(System.out::println);
        }

//        chatLogRepository.findById(chatLogId).ifPresent(chatLog -> {
//            chatLog.getUsers().addAll(userList);
//            chatLogRepository.save(chatLog);
//        });

//        return Collections.singletonList(UserResponseDto.builder().build());
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

}