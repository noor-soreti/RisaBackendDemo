package org.example.risabackend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userRequestDtos = new ArrayList<>();
        for (User user : users) {
            userRequestDtos.add(
                    UserResponseDto.builder()
                            .id(user.getId())
                            .fullName(user.getFullName())
                            .phoneNumber(user.getPhoneNumber())
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

    public UserResponseDto getUserDtoById(Long id) {
        User userEntity = userRepository.findFirstById(id);

        return UserResponseDto.builder()
                .id(userEntity.getId())
                .fullName(userEntity.getFullName())
                .phoneNumber(userEntity.getPhoneNumber())
                .profilePicture(userEntity.getProfilePicture())
                .status(userEntity.getStatus())
                .lastSeen(userEntity.getLastSeen())
                .isOnline(userEntity.isOnline())
                .chatLogs(userEntity.getChatLogs())
                .build();
    }

    public UserResponseDto getUserByPhoneNumber(String phoneNumber) {
        User userEntity = userRepository.findByPhoneNumber(phoneNumber);
        if (userEntity == null) {
            return null;
        }
        return UserResponseDto.builder()
                .id(userEntity.getId())
                .fullName(userEntity.getFullName())
                .phoneNumber(userEntity.getPhoneNumber())
                .profilePicture(userEntity.getProfilePicture())
                .status(userEntity.getStatus())
                .lastSeen(userEntity.getLastSeen())
                .isOnline(userEntity.isOnline())
                .chatLogs(userEntity.getChatLogs())
                .build();
    }

    public UserResponseDto createUser(User newUser) {
        User user = userRepository.findByPhoneNumber(newUser.getPhoneNumber());

        if (user != null) {
            System.out.println("createUser: COULD NOT CREATE USER");
            return null;
        }

        userRepository.save(newUser);

        return UserResponseDto.builder()
                .id(newUser.getId())
                .fullName(newUser.getFullName())
                .phoneNumber(newUser.getPhoneNumber())
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