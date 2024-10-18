package org.example.risabackend.contact;

import org.example.risabackend.exceptions.ContactAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // GET

    @GetMapping()
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/id/{uid}")
    public ResponseEntity<Set<Contact>> getContacts(@PathVariable Long uid) {
        Set<Contact> contactSet = contactService.getContactsFromUserId(uid);

        if (contactSet == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(contactSet);
    }

    @PostMapping("/id/{uid}/{contactId}")
    public ResponseEntity<Contact> createContact(@PathVariable Long uid, @PathVariable Long contactId) {
        Contact contact = contactService.addContact(uid, contactId);
        return ResponseEntity.status(HttpStatus.CREATED).body(contact);
    }

    // DELETE
    @DeleteMapping("/all")
    public void deleteAllContacts() {
        contactService.deleteAllContacts();
    }

    // EXCEPTION HANDLING
    @ExceptionHandler(ContactAlreadyExistsException.class)
    public ResponseEntity<String> handleContactAlreadyExists(ContactAlreadyExistsException ex) {
        System.out.println(ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
