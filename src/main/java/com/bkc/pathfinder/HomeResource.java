package com.bkc.pathfinder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {
	
	@GetMapping("/")
	public String home() {
		return ("<h1>WELCOME</h1>");
	}
	
	@GetMapping("/api/internal/admin")
	public String admin() {
		return ("<h1>WELCOME ADMIN</h1>");
	}
	
	@GetMapping("/api/crm/user")
	public String user() {
		return ("<h1>WELCOME USER</h1>");
	}
	
	@GetMapping("/api/external/reddit")
	public String reddit() {
		return ("<h1>WELCOME TO REDDIT</h1>");
	}

}
