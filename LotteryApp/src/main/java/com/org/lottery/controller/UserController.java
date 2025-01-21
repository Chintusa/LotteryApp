package com.org.lottery.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.lottery.dto.User;
import com.org.lottery.respository.UserRepository;
import com.org.lottery.service.MailServices;
import com.org.lottery.utility.OtpGenerator;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository repo;
	@Autowired
	private MailServices emailService;

	private User user;
	private static String otp;

	@PostMapping("/send-otp")
	public ResponseEntity<Map<String, String>> sendNotification(@RequestBody Map<String, String> request) {
		String email = request.get("email");
//		System.out.println(email);
		if (email == null || email.isEmpty()) {
			return ResponseEntity.badRequest().body(Map.of("message", "Valid Email is required"));
		}
		try {
			otp = OtpGenerator.generateOtp();
//			System.out.println(otp);
			emailService.sentMail(email, "Verify OTP", "Please go through this mail for OTP ", otp);
			return ResponseEntity.ok(Map.of("message", "OTP sent successfully!"));
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("message", "Failed to send OTP", "error", e.getMessage()));
		}

	}

	@PostMapping("/verify-otp")
	public ResponseEntity<Map<String, Boolean>> verifyOtp(@RequestBody Map<String, String> request) {
		String sent_otp = request.get("otp");
		Map<String, Boolean> response = new HashMap<>();

		if (otp.equals(sent_otp)) {
			response.put("valid", true);
		} else {
			response.put("valid", false);
		}

		return ResponseEntity.ok(response);
	}

	@GetMapping("/submit")
	@ResponseBody
	public ResponseEntity<User> getUser(@RequestParam String name, @RequestParam long mobile,
			@RequestParam String email, @RequestParam String password, @RequestParam String repassword) {

		user = new User();
		user.setId(user.getId());
		user.setName(name);
		user.setMobile(mobile);
		user.setEmail(email);

		if (password.equals(repassword))
			user.setPassword(password);
		else
			return ResponseEntity.badRequest().build();

		return ResponseEntity.ok(repo.save(user));

	}

}
