package com.bkc.pathfinder.repository.contact;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bkc.pathfinder.model.activity.Activity;
import com.bkc.pathfinder.model.contact.Contact;
import com.bkc.pathfinder.model.contact.ContactActivity;
import com.bkc.pathfinder.projection.ContactActivityProjection;

/**
 * 
 * @author bumki
 *
 */

@Repository
public interface ContactActivityRepository extends JpaRepository<ContactActivity, String> {
	
	List<ContactActivity> findAllByContact(@Param("contact") Contact contact);

	List<ContactActivity> findAllByActivity(@Param("activity") Activity activity);

}
