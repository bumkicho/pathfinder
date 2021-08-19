package com.bkc.pathfinder.service.contact;

import org.springframework.stereotype.Service;

import com.bkc.pathfinder.model.contact.Contact;
import com.bkc.pathfinder.model.contact.ContactActivity;
import com.bkc.pathfinder.model.contact.ContactAddress;

/**
 * 
 * @author bumki
 *
 */
@Service
public interface ContactServiceInterface {

	Contact saveContact(Contact contact);

	ContactActivity saveContactActivity(ContactActivity contactActivity);

	ContactAddress saveAddress(ContactAddress contactAddress);

}
