package com.aluraChallenge.literAlura;

import com.aluraChallenge.literAlura.controller.AuthorController;
import com.aluraChallenge.literAlura.controller.BookController;
import com.aluraChallenge.literAlura.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
	@Autowired
    AuthorController authorController;

	@Autowired
	BookController bookController;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(authorController, bookController);
		principal.welcome();
		principal.showMenu();
	}
}