//package com.org.lottery.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.org.lottery.service.MailServices;
//
//@RestController
//public class MailController {
//
//	@Autowired
//	private MailServices emailService;
//
//	@PostMapping("/send-email")
//	public String sendNotification(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
////		System.out.println("Sending email...");
//		emailService.setMail(to, subject, body);
//		return "Email sent successfully!";
//	}
//}
