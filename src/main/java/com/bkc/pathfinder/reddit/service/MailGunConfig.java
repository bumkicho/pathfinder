package com.bkc.pathfinder.reddit.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Component
@AllArgsConstructor
@NoArgsConstructor
public class MailGunConfig {
	
	@Value("${mailgun.api.domain}")
	private String mailGunDomain;
	
	@Value("${mailgun.api.key}")
	private String mailGunApiKey;
	
	@Value("${mailgun.api.sender}")
	private String adminSenderAddress;

}
