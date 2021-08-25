package com.bkc.pathfinder.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkc.pathfinder.exception.PFException;
import com.bkc.pathfinder.model.activity.Activity;
import com.bkc.pathfinder.model.activity.ActivityType;
import com.bkc.pathfinder.model.contact.Contact;
import com.bkc.pathfinder.model.contact.ContactActivity;
import com.bkc.pathfinder.service.activity.ActivityServiceInterface;
import com.bkc.pathfinder.service.contact.ContactServiceInterface;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

/**
 * 
 * @author bumki
 *
 */

@SuppressWarnings("deprecation")
@RestController
@RequestMapping("api/crm/activity")
public class ActivityController {
	
	@Autowired
	private ActivityServiceInterface activityService;
	
	@Autowired
	private ContactServiceInterface contactService;

	@PostMapping("add")
	public ResponseEntity<?> addActivity(@RequestBody ObjectNode objectNode) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JSR310Module());
		
		JsonNode rootNode = null;
		
		try {
			rootNode = mapper.readTree(objectNode.toString());
			
			rootNode.get("elementcount").asInt();
			
			rootNode.get("elements").forEach(jsonNode -> {
				String emailAddress = jsonNode.get("emailAddress").asText();
				Contact contact = contactService.findByEmailAddress(emailAddress);
				if(contact==null) {
					throw new PFException("Contact was not found");
				}
				
				jsonNode.get("activity").forEach(activityNode -> {
					String activityTypeCode = activityNode.get("activityType").asText();
					Optional<ActivityType> activityType = activityService.findActivityTypeByTypeCode(activityTypeCode);
					activityType.orElseThrow(() -> new PFException("Invalid Activity Type"));

					Activity activity = mapper.convertValue(activityNode, Activity.class);
					activity.setActivityType(activityType.get());
					activityService.saveActivity(activity);
					
					ContactActivity contactActivity = new ContactActivity();
					contactActivity.setActivity(activity);
					contactActivity.setContact(contact);
					contactService.saveContactActivity(contactActivity);
				});
			});
			return new ResponseEntity<>("Activity was added successfully.", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Activity was not added successfully. Please check your json body", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("update")
	public ResponseEntity<?> updateActivity(@RequestBody ObjectNode objectNode) {
		return new ResponseEntity<>("Not Implemented", HttpStatus.NOT_IMPLEMENTED);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<?> deleteActivity(@RequestBody ObjectNode objectNode) {
		return new ResponseEntity<>("Not Implemented", HttpStatus.NOT_IMPLEMENTED);
	}

	@PostMapping("addtype")
	public ResponseEntity<?> addActivityType(@RequestBody ActivityType activityType) {
		
		return new ResponseEntity<ActivityType>(activityService.saveActivityType(activityType), HttpStatus.OK);
		
	}
}
