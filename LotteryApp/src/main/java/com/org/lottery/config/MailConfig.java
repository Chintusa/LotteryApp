//package com.org.lottery.config;
//
//import java.util.Properties;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//@Configuration
//public class MailConfig {
//
//	@Bean
//	public JavaMailSender getJavaMailSender() {
//		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//
//		mailSender.setHost("smtp.gmail.com");
//		mailSender.setPort(587);
//		mailSender.setUsername("jhasaketansa86@gmail.com");
//		mailSender.setPassword("sbfi efln rvls bync"); // Ensure the password is correct
//
//		Properties props = mailSender.getJavaMailProperties();
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//		props.put("mail.smtp.timeout", "5000");
//		props.put("mail.smtp.connectiontimeout", "5000");
//
//		return mailSender;
//	}
//}
