package com.bkc.pathfinder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bkc.pathfinder.reddit.service.MailService;
import com.mashape.unirest.http.JsonNode;

/**
 * 
 * @author bumki
 *
 */

@SpringBootTest
public class SendTestEmail {

	@Autowired
	private MailService mailService;
	
	@Test
	public void testEmail() {
//		JsonNode node = mailService.sendTestMailViaMailGun();
//		System.out.println(node.toString());
	}
}
