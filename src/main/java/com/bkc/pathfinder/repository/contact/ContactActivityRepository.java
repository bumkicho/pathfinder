package com.bkc.pathfinder.repository.contact;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bkc.pathfinder.model.activity.Activity;
import com.bkc.pathfinder.model.contact.Contact;
import com.bkc.pathfinder.model.contact.ContactActivity;

/**
 * 
 * @author bumki
 *
 */

@Repository
public interface ContactActivityRepository extends JpaRepository<ContactActivity, String> {

	// TODO: figure out how to find all contact activities
	Optional<List<Activity>> findAllByContact(@Param("contact") Contact contact);
}
