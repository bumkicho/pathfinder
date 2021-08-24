package com.bkc.pathfinder;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bkc.pathfinder.model.activity.Activity;
import com.bkc.pathfinder.model.activity.ActivityType;
import com.bkc.pathfinder.model.contact.Contact;
import com.bkc.pathfinder.model.contact.ContactActivity;
import com.bkc.pathfinder.model.contact.ContactAddress;
import com.bkc.pathfinder.service.activity.ActivityService;
import com.bkc.pathfinder.service.contact.ContactService;

@SpringBootTest
public class ContactActivityTest {
	
	@Autowired
	ActivityService activityService;
	
	@Autowired
	ContactService contactService;
	
	@Test
	public void addContactActivity() {
		Contact contact = addContact();
		ContactAddress contactAddress = addContactAddress(contact);
		ActivityType activityType = addActivityType();
		Activity activity = addActivity(activityType);		
		addContactActivity(contact, activity);
	}
	
	private void addContactActivity(Contact contact, Activity activity) {
		ContactActivity contactActivity = new ContactActivity();
		contactActivity.setActivity(activity);
		contactActivity.setContact(contact);
		
		contactService.saveContactActivity(contactActivity);		
	}

	private Contact addContact() {
		Contact contact = new Contact();
		contact.setFirstName("BumKi");
		contact.setLastName("Cho");
		contact.setEmailAddress("bumkicho@icloud.com");
		
		return contactService.saveContact(contact);
	}
	
	private ContactAddress addContactAddress(Contact contact) {
		ContactAddress contactAddress = new ContactAddress();
		contactAddress.setContact(contact);
		contactAddress.setAddressLine1("8425 Tumbleweed St.");
		contactAddress.setCity("Portage");
		contactAddress.setState("MI");
		contactAddress.setPostalCode1("49002");
		
		return contactService.saveAddress(contactAddress);
	}
	
	private ActivityType addActivityType() {
		ActivityType activityType = new ActivityType();
		activityType.setDescription("Email");
		
		return activityService.saveActivityType(activityType);
	}
	
	private Activity addActivity(ActivityType activityType) {
		
		Activity activity = new Activity();
		activity.setActivityType(activityType);
		activity.setScheduledStartDt(LocalDateTime.now());
		activity.setScheduledEndDt(LocalDateTime.now());
		activity.setSubject("this is time");
		activity.setNotes("Is my next destination Theorem? Please God guide me through");
		
		return activityService.saveActivity(activity);
	}
	

}
