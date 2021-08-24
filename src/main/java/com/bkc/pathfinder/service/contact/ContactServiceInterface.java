package com.bkc.pathfinder.service.contact;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bkc.pathfinder.model.contact.Contact;
import com.bkc.pathfinder.model.contact.ContactActivity;
import com.bkc.pathfinder.model.contact.ContactAddress;
import com.bkc.pathfinder.projection.ContactAddressProjection;

/**
 * 
 * @author bumki
 *
 */
@Service
public interface ContactServiceInterface {

	Contact findByEmailAddress(String emailAddress);
	List<Contact> findAllContacts();
	
	Contact saveContact(Contact contact);

	ContactActivity saveContactActivity(ContactActivity contactActivity);

	ContactAddress saveAddress(ContactAddress contactAddress);
	ContactAddress findAddressByContact(Contact contact);
	List<ContactAddressProjection> findAllContactAddress();

}
