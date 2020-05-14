package com.yh.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailMan {
	@Value("${spring.mail.username}")
	private String sendFromEmailAddress;
	private JavaMailSender mailSender;
	
	@Autowired
	public MailMan(JavaMailSender mailSender ) {
		this.mailSender= mailSender;
	}
	
	public void sender(String sendToAddress, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
	
		message.setFrom(sendFromEmailAddress);
		message.setTo(sendToAddress);
		message.setSubject(subject);
		message.setText(text);
				
		mailSender.send(message);
		
	}

}
