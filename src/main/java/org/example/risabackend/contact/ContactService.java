package org.example.risabackend.contact;

import org.example.risabackend.chatlog.ChatLog;
import org.example.risabackend.chatlog.ChatLogRepository;
import org.example.risabackend.user.User;
import org.example.risabackend.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ContactService {
    private ContactRepository contactRepository;
    private UserRepository userRepository;
    private ChatLogRepository chatLogRepository;

    public ContactService(ContactRepository contactRepository, UserRepository userRepository, ChatLogRepository chatLogRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
        this.chatLogRepository = chatLogRepository;
    }

    public Set<Contact> getContactsFromUserId(Long userId) {
        User user = userRepository.findFirstById(userId);

        if (user == null) {
            return null;
        }

        return user.getContacts();
    }

    public Contact addContact(Long userId, Long newContactId) {
        User currentUser = userRepository.findFirstById(userId);
        User contactUser = userRepository.findFirstById(newContactId);
        if (currentUser == null) {
            return null;
        }

        // add contact to current user
        Contact contact = new Contact(userId, newContactId, System.currentTimeMillis(), currentUser.getFullName());
        currentUser.getContacts().add(contact);
        userRepository.saveAndFlush(currentUser);

        // add chat log
        Set<User> userSet = new HashSet<>();
        userSet.add(contactUser);
        userSet.add(currentUser);
        ChatLog newChatLog = new ChatLog(userSet);
        chatLogRepository.save(newChatLog);

        return contact;
    }

    public void deleteAllContacts() {
        contactRepository.deleteAll();
    }

}