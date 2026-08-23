package com.project.sentinleops;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SentinleopsApplication {
	static {
		// pgJDBC passes the JVM timezone during connection startup. Use the portable UTC ID
		// because the database rejects this machine's legacy Asia/Calcutta timezone ID.
		System.setProperty("user.timezone", "UTC");
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(SentinleopsApplication.class, args);
	}

}
