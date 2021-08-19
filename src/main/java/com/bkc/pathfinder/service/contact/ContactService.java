package com.bkc.pathfinder.service.contact;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkc.pathfinder.model.contact.Contact;
import com.bkc.pathfinder.model.contact.ContactActivity;
import com.bkc.pathfinder.model.contact.ContactAddress;
import com.bkc.pathfinder.repository.contact.ContactActivityRepository;
import com.bkc.pathfinder.repository.contact.ContactAddressRepository;
import com.bkc.pathfinder.repository.contact.ContactRepository;

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
	

}
