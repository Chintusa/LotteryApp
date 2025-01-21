package com.org.lottery.utility;

import java.security.SecureRandom;

public class OtpGenerator {

	private static SecureRandom rd = new SecureRandom();

	public static String generateOtp() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 4; i++)
			builder.append(rd.nextInt(10));
		return builder.toString();
	}
}
