package com.Jones.mscart;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MscartApplication {

	public static void main(String[] args) {

		// 1️⃣ Load .env FIRST
		Dotenv dotenv = Dotenv.configure()
				.directory("./")
				.ignoreIfMalformed()
				.ignoreIfMissing()
				.load();

		if (dotenv.get("DB_URL") != null) {
			System.setProperty("DB_URL", dotenv.get("DB_URL"));
		}
		if (dotenv.get("DB_USER") != null) {
			System.setProperty("DB_USER", dotenv.get("DB_USER"));
		}
		if (dotenv.get("DB_PASS") != null) {
			System.setProperty("DB_PASS", dotenv.get("DB_PASS"));
		}

		// 2️⃣ THEN start Spring
		SpringApplication.run(MscartApplication.class, args);
	}
}
