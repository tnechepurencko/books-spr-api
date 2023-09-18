package org.example;

import org.example.database.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// change password in Database getConnection
// 3 first lines in app.prop
// TGClient ADMIN_ID
// TGClient apiToken
// TGClient sessionPath
// TGClient authenticationData
// TGClient enable output to file

@SpringBootApplication
public class SpringSecurityApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);

	}

}
