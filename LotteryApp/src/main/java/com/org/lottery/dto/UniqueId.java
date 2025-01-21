package com.org.lottery.dto;

import java.util.UUID;

public class UniqueId {

	public static String generateUniqueId() {
		return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
	}

}
