package com.fileupload.FileUpload;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileUploadApplication {

	public static void main(String[] args) {

		Dotenv dot = Dotenv.load();
		System.setProperty("DB_URL",dot.get("DB_URL"));
		System.setProperty("DB_DATABASE",dot.get("DB_DATABASE"));
		SpringApplication.run(FileUploadApplication.class, args);
	}

}
