package com.bkc.pathfinder.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkc.pathfinder.config.security.PFUserDetails;
import com.bkc.pathfinder.model.contact.Contact;
import com.bkc.pathfinder.model.contact.ContactAddress;
import com.bkc.pathfinder.service.contact.ContactServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 
 * @author bumki
 *
 */

/*
 * ContactController is API end-point controller
 */
@RestController
@RequestMapping("api/crm/contact")
public class ContactController {
	
	@Autowired
	private ContactServiceInterface contactService;
	
	/*
	 * extract current user ID from @AuthenticationPrincipal
	 * may need to filter by user to display contacts
	 */
	@GetMapping
	public ResponseEntity<?> getAllContacts(@AuthenticationPrincipal PFUserDetails currentUser) {
		String userId = currentUser.getUserId();
		return new ResponseEntity<>(contactService.findAllContactAddress(), HttpStatus.OK);
	}
	
	@PutMapping("update")
	public ResponseEntity<?> updateContact(@RequestBody Contact contact) {
		String firstName = contact.getFirstName();
		String lastName = contact.getLastName();
		String emailAddress = contact.getEmailAddress();
		String contactId = contact.getContactId();
		
		Contact existingContact = contactService.findByEmailAddress(emailAddress);
		
		if(!contactId.equalsIgnoreCase(existingContact.getContactId())) {
			return new ResponseEntity<>("email address already exists", HttpStatus.BAD_REQUEST);
		} else {
			existingContact.setFirstName(firstName);
			existingContact.setLastName(lastName);
			contactService.saveContact(existingContact);
			return new ResponseEntity<>("contact was saved", HttpStatus.OK);
		}
	}
	
	@PutMapping("updateaddress")
	public ResponseEntity<?> updateAddress(@RequestBody ObjectNode objectNode) {
		
		String emailAddress = objectNode.get("emailAddress").asText();
		Contact contact = contactService.findByEmailAddress(emailAddress);
		if(contact==null) {
			return new ResponseEntity<>("no contact was found", HttpStatus.BAD_REQUEST);
		}
		
		String addressLine1 = objectNode.get("addressLine1").asText();
		String addressLine2 = objectNode.get("addressLine2").asText();
		String city = objectNode.get("city").asText();
		String state = objectNode.get("state").asText();
		String postalCode1 = objectNode.get("postalCode1").asText();
		String postalCode2 = objectNode.get("postalCode2").asText();
		
		ContactAddress address = contactService.findAddressByContact(contact);
		if(address==null) {
			address = new ContactAddress();
			address.setContact(contact);
			address.setAddressLine1(addressLine1);
			address.setAddressLine2(addressLine2);
			address.setCity(city);
			address.setState(state);
			address.setPostalCode1(postalCode1);
			address.setPostalCode2(postalCode2);
		} else {
			address.setAddressLine1(addressLine1);
			address.setAddressLine2(addressLine2);
			address.setCity(city);
			address.setState(state);
			address.setPostalCode1(postalCode1);
			address.setPostalCode2(postalCode2);			
		}
		
		contactService.saveAddress(address);
		
		return new ResponseEntity<>("update address successfully", HttpStatus.OK);
	}	
	
	@PostMapping("add") //api/crm/contact/add
	public ResponseEntity<?> addContact(@RequestBody ObjectNode objectNode) {
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = null;

		try {
			rootNode = mapper.readTree(objectNode.toString());
		} catch (JsonProcessingException e) {
			return new ResponseEntity<>("json processing exception", HttpStatus.BAD_REQUEST);
		}
		
		if(rootNode==null || (rootNode!=null && getElementCount(rootNode)==0)) {
			return new ResponseEntity<>("no contact was passed", HttpStatus.BAD_REQUEST);
		}
		
		JsonNode contactAndAddress = rootNode.path("elements");
		List<APIResponseMessage> pms = new ArrayList<APIResponseMessage>();
		for (JsonNode node : contactAndAddress) {
			//TODO: create object to represent element and process it via service class
			pms.add(addContactAndAddress(node));
		}
		
		Long success = pms.stream().filter(pm->pm.getMsg().equalsIgnoreCase("Success")).count();
		Long error = pms.stream().filter(pm->pm.getMsg().equalsIgnoreCase("Error")).count();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(success + " contact/address elements were successfully processed. ");
		stringBuilder.append(error + " contact/address elements were errored.");
		
		return new ResponseEntity<>(stringBuilder.toString(), HttpStatus.OK);
	}

	private APIResponseMessage addContactAndAddress(JsonNode node) {
		String firstName = node.get("firstName").asText();
		String lastName = node.get("lastName").asText();
		String emailAddress = node.get("emailAddress").asText();
		
		String addressLine1 = node.get("addressLine1").asText();
		String addressLine2 = node.get("addressLine2").asText();
		String city = node.get("city").asText();
		String state = node.get("state").asText();
		String postalCode1 = node.get("postalCode1").asText();
		String postalCode2 = node.get("postalCode2").asText();
		
		Contact contact = null;
		ContactAddress address = null;
		APIResponseMessage processMessage = getProcessMessage();
		
		if((firstName==null|| firstName.isBlank()) || (emailAddress==null || emailAddress.isBlank())) {
			processMessage.setMsg("Error");
		} else if(contactService.findByEmailAddress(emailAddress)!=null) {
			processMessage.setMsg("Error");
		} else {
			contact = new Contact();
			contact.setFirstName(firstName);
			contact.setLastName(lastName);
			contact.setEmailAddress(emailAddress);
			contact = contactService.saveContact(contact);
			
			processMessage.setMsg("Success");
		}
		
		if(contact!=null && (city!=null && !city.isBlank()) && (state!=null && !state.isBlank())) {
			address = new ContactAddress();
			address.setContact(contact);
			address.setAddressLine1(addressLine1);
			address.setAddressLine2(addressLine2);
			address.setCity(city);
			address.setState(state);
			address.setPostalCode1(postalCode1);
			address.setPostalCode2(postalCode2);
			
			contactService.saveAddress(address);
			
			processMessage.setMsg("Success");
		}
		
		return processMessage;
		
	}
	
	private int getElementCount(JsonNode rootNode) {
		try {
			return rootNode.get("elementcount").asInt();
		} catch(NullPointerException ex) {
			return 0;
		}
	}
	
	@Bean
	public APIResponseMessage getProcessMessage() {
		return new APIResponseMessage();
	}

}
