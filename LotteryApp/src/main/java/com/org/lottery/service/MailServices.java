package com.org.lottery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailServices {

	@Autowired
	private JavaMailSender javaMail;

	@Async
	public void sentMail(String to, String sub, String body, String otp) {
		MimeMessage message = javaMail.createMimeMessage();
		MimeMessageHelper helper;

		try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom("jhasaketansa86@gmail.com");
			helper.setTo(to);
			helper.setSubject(sub);
			helper.setText("" + body + "\n" + otp);
			javaMail.send(message);
			System.out.println(otp);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
