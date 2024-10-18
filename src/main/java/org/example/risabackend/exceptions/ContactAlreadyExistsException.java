package org.example.risabackend.exceptions;

public class ContactAlreadyExistsException extends RuntimeException {
  public ContactAlreadyExistsException(String message) {
    super(message);
  }
}
