package com.bkc.pathfinder.repository.contact;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bkc.pathfinder.model.contact.Contact;

/**
 * 
 * @author bumki
 *
 */

/*
 * ContactRepository declares methods to be used in ContactService class
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	Contact findByFirstName(String firstName);
	Contact findByEmailAddress(String emailAddress);

}
