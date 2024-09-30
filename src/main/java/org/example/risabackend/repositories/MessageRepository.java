package org.example.risabackend.repositories;

import org.example.risabackend.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 JpaRepository extends PagingAndSortingRepository which extends CrudRepository
 main functionalities:
 1. CrudRepository mainly provides CRUD functions.
 2. PagingAndSortingRepository provides methods to do pagination and sorting records.
 3. JpaRepository provides some JPA-related methods such as flushing the persistence context and deleting records in a batch.
*/
public interface MessageRepository extends JpaRepository<Message, Long> {
}
