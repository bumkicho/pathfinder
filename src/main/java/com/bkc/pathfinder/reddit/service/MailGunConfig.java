package com.bkc.pathfinder.reddit.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class MailGunConfig {
	
	@Value("${mailgun.api.domain}")
	private String mailGunDomain;
	
	@Value("${mailgun.api.key}")
	private String mailGunApiKey;

}
