package com.bkc.pathfinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkc.pathfinder.model.user.Role;
import com.bkc.pathfinder.model.user.User;
import com.bkc.pathfinder.service.user.UserServiceInterface;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 
 * @author bumki
 *
 */

@RestController
@RequestMapping("api/internal")
public class InternalApiController {
	
	@Autowired
	private UserServiceInterface iUserService;
	
	@PutMapping("updateuser")
	public ResponseEntity<?> updateUser(@RequestBody ObjectNode objectNode) {
		String userName = objectNode.get("username").asText();
		String roleName = objectNode.get("rolename").asText();
		String status = objectNode.get("status").asText();
		
		User user = iUserService.findByUserName(userName);
		Role role;
		
		if(user==null) {
			return new ResponseEntity<>("User was not found", HttpStatus.NOT_FOUND);
		}
		
		if(roleName!=null && !roleName.isBlank()) {
			role = iUserService.findRoleByRoleName(roleName);
			if(role==null) {
				return new ResponseEntity<>("Invalid role name was passed", HttpStatus.NOT_FOUND);
			}
			iUserService.setRolesForUser(user, role);
		}
		
		if(status!=null && !status.isBlank()) {
			if(status.equalsIgnoreCase("Active")) {
				user.setActive(true);
			} else if(status.equalsIgnoreCase("Inactive")) {
				user.setActive(false);
			} else {
				return new ResponseEntity<>("Invalid status was passed", HttpStatus.BAD_REQUEST);
			}
		}		
		
		return new ResponseEntity<>("User update was successful", HttpStatus.OK);
	}
	
	@PostMapping("addrole")
	public ResponseEntity<?> addRole(@RequestBody Role role) {
		if(iUserService.findRoleByRoleName(role.getRoleName())!=null) {
			return new ResponseEntity<>("Role already exists", HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<Role>(iUserService.saveRole(role), HttpStatus.CREATED);
	}

}
