package org.example.risabackend.contact;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.example.risabackend.chatlog.ChatLog;
import org.example.risabackend.chatlog.ChatLogRepository;
import org.example.risabackend.contact.dto.ContactsResponseDto;
import org.example.risabackend.exceptions.ContactAlreadyExistsException;
import org.example.risabackend.user.User;
import org.example.risabackend.user.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Slf4j
@Service
public class ContactService {
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    public ContactService(ContactRepository contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    public Set<Contact> getUserContacts(Long userId) {
        User user = userRepository.findFirstById(userId);
        Set<ContactsResponseDto> contactsResponseDtos = new HashSet<>();

        if (user == null) {
            throw new EntityNotFoundException("User not found");
        }

        return user.getContacts();
    }

    public Contact addContact(Long userId, Long newContactId) {
        User currentUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with user_id " + userId + " not found"));

        User contact = userRepository.findById(newContactId)
                .orElseThrow(() -> new EntityNotFoundException("User with contact_id " + newContactId + " not found"));

        boolean contactExists = currentUser.getContacts()
                .stream()
                .anyMatch(c -> c.getContact_id().equals(newContactId));

        if (contactExists) {
            System.out.println("contactExists");
            throw new ContactAlreadyExistsException("User already has contact with id " + newContactId + " in their contacts list!");
        }

        Contact newContact = new Contact(currentUser.getId(), contact.getId(), new Timestamp(System.currentTimeMillis()), contact.getFullName());
        contactRepository.save(newContact);

        return newContact;
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