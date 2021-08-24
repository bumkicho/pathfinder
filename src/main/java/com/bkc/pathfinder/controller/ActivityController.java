package com.bkc.pathfinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkc.pathfinder.service.activity.ActivityServiceInterface;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 
 * @author bumki
 *
 */

@RestController
@RequestMapping("api/crm/activity")
public class ActivityController {
	
	@Autowired
	private ActivityServiceInterface activityService;
	
	@PostMapping("add")
	public ResponseEntity<?> addActivity(@RequestBody ObjectNode objectNode) {
		//activityService.saveActivity(null)
		return new ResponseEntity<>("Not implemented", HttpStatus.NOT_IMPLEMENTED);
	}
	
	@PutMapping("update")
	public ResponseEntity<?> updateActivity(@RequestBody ObjectNode objectNode) {
		return new ResponseEntity<>("Not Implemented", HttpStatus.NOT_IMPLEMENTED);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<?> deleteActivity(@RequestBody ObjectNode objectNode) {
		return new ResponseEntity<>("Not Implemented", HttpStatus.NOT_IMPLEMENTED);
	}

}
