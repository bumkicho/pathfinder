package com.bkc.pathfinder.service.contact;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkc.pathfinder.model.contact.Contact;
import com.bkc.pathfinder.model.contact.ContactActivity;
import com.bkc.pathfinder.model.contact.ContactAddress;
import com.bkc.pathfinder.projection.ContactAddressProjection;
import com.bkc.pathfinder.repository.contact.ContactActivityRepository;
import com.bkc.pathfinder.repository.contact.ContactAddressRepository;
import com.bkc.pathfinder.repository.contact.ContactRepository;

/**
 * 
 * @author bumki
 *
 */

/*
 * ContactService autowires multiple contact related repositories
 * it defines methods to be used by ContactController class
 */
@Service
public class ContactService implements ContactServiceInterface {
	
	@Autowired
	ContactRepository contactRepository;
	
	@Autowired
	ContactActivityRepository contactActivityRepository;
	
	@Autowired
	ContactAddressRepository contactAddressRepository;
	
	@Override
	public Contact saveContact(Contact contact) {
		contact.setFullName(contact.getFirstName()+" "+contact.getLastName());
		contact.setCreatedDt(LocalDateTime.now());
		return contactRepository.save(contact);
	}

	@Override
	public ContactActivity saveContactActivity(ContactActivity contactActivity) {
		contactActivity.setCreatedDt(LocalDateTime.now());
		return contactActivityRepository.save(contactActivity);		
	}

	@Override
	public ContactAddress saveAddress(ContactAddress contactAddress) {
		return contactAddressRepository.save(contactAddress);
	}
	
	@Override
	public List<Contact> findAllContacts() {
		return contactRepository.findAll();
	}

	@Override
	public Contact findByEmailAddress(String emailAddress) {
		return contactRepository.findByEmailAddress(emailAddress);
	}

	@Override
	public ContactAddress findAddressByContact(Contact contact) {
		return contactAddressRepository.findByContact(contact);
	}

	@Override
	public List<ContactAddressProjection> findAllContactAddress() {
		return contactAddressRepository.findAllContactAddress();
	}

}
