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

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	Optional<Contact> findByFirstName(String firstName);

}
