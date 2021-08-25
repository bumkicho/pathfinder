package com.bkc.pathfinder.reddit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import com.bkc.pathfinder.exception.PFException;
import com.bkc.pathfinder.reddit.model.NotificationEmail;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailService {

	private final MailContentBuilder mailContentBuilder;
	
	@Autowired
	private MailGunConfig mailGunConfig;
	
	public JsonNode sendMailViaMailGun(NotificationEmail notificationEmail) {
	
		String mailGunDomain = this.mailGunConfig.getMailGunDomain();
		String mailGunApiKey = this.mailGunConfig.getMailGunApiKey();

		HttpResponse<JsonNode> request;
		try {
			request = Unirest.post("https://api.mailgun.net/v3/"+mailGunDomain+"/messages")
					.basicAuth("api", mailGunApiKey)
					.field("from", "bumkicho@gmail.com").field("to", notificationEmail.getRecipient())
					.field("subject", notificationEmail.getSubject())
					.field("text", mailContentBuilder.build(notificationEmail.getBody()))
					.asJson();
			return request.getBody();
		} catch (UnirestException e) {
			throw new PFException("Email was not successfully sent", e);
		}

	}
	
	public JsonNode sendTestMailViaMailGun() {
		
		String mailGunDomain = this.mailGunConfig.getMailGunDomain();
		String mailGunApiKey = this.mailGunConfig.getMailGunApiKey();

		HttpResponse<JsonNode> request;
		try {
			request = Unirest.post("https://api.mailgun.net/v3/"+mailGunDomain+"/messages")
					.basicAuth("api", mailGunApiKey)
					.field("from", "bumkicho@gmail.com").field("to", "bumkicho@gmail.com")
					.field("subject", "hello").field("text", "this is a test email").asJson();
			return request.getBody();
		} catch (UnirestException e) {
			throw new PFException("Email was not successfully sent", e);
		}

	}

}
