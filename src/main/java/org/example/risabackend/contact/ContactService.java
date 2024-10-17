package org.example.risabackend.contact;

import lombok.extern.slf4j.Slf4j;
import org.example.risabackend.chatlog.ChatLog;
import org.example.risabackend.chatlog.ChatLogRepository;
import org.example.risabackend.user.User;
import org.example.risabackend.user.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
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

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
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

        if (contactUser == null) {
            return null;
        }

        Contact contact = new Contact(currentUser.getId(), contactUser.getId(), new Timestamp(System.currentTimeMillis()), contactUser.getFullName());
        System.out.println(contact);
        contactRepository.save(contact);

        return contact;


//        User contactUser = userRepository.findFirstById(newContactId);
//
//        if (contactUser == null) {
//            return null;
//        }
//
//        Set<Contact> contacts = userRepository.findById(userId)
//                .map(user -> {
//                    Contact contact = new Contact(user.getId(), contactUser.getId(), new Timestamp(System.currentTimeMillis()), contactUser.getFullName());
////                    contactRepository.save(contact);
//                    user.getContacts().add(contact);
//                    return user.getContacts();
//                })
//                .orElseThrow(() -> new RuntimeException("addContact EXCEPTION"));
//
//        return contacts;
    }

    public void deleteAllContacts() {
        contactRepository.deleteAll();
    }

}