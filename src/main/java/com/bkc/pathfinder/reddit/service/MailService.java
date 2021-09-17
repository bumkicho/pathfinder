package com.bkc.pathfinder.reddit.service;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
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

	/*
	 * Annotation that marks a method as a candidate for <i>asynchronous</i> execution.
	 * Can also be used at the type level, in which case all of the type's methods are
	 * considered as asynchronous. Note, however, that {@code @Async} is not supported
	 * on methods declared within a
	 * {@link org.springframework.context.annotation.Configuration @Configuration} class.
	 */
	@Async
	public JsonNode sendMailViaMailGun(NotificationEmail notificationEmail) {

		MailGunConfig mailGunConfig = getMailGunConfig();
		
		String mailGunDomain = mailGunConfig.getMailGunDomain();
		String mailGunApiKey = mailGunConfig.getMailGunApiKey();
		String mailSender = mailGunConfig.getAdminSenderAddress();

		HttpResponse<JsonNode> request;
		try {
			request = Unirest.post("https://api.mailgun.net/v3/"+mailGunDomain+"/messages")
					.basicAuth("api", mailGunApiKey)
					.field("from", mailSender).field("to", notificationEmail.getRecipient())
					.field("subject", notificationEmail.getSubject())
					.field("text", mailContentBuilder.build(notificationEmail.getBody()))
					.asJson();
			return request.getBody();
		} catch (UnirestException e) {
			throw new PFException("Email was not successfully sent", e);
		}

	}
	
	@Bean
	private MailGunConfig getMailGunConfig() {
		return new MailGunConfig();
	}

//	public JsonNode sendTestMailViaMailGun() {
//
//		HttpResponse<JsonNode> request;
//		try {
//			request = Unirest.post("https://api.mailgun.net/v3/"+mailGunDomain+"/messages")
//					.basicAuth("api", mailGunApiKey)
//					.field("from", mailSender).field("to", "bumkicho@gmail.com")
//					.field("subject", "hello").field("text", "this is a test email").asJson();
//			return request.getBody();
//		} catch (UnirestException e) {
//			throw new PFException("Email was not successfully sent", e);
//		}
//
//	}

}
