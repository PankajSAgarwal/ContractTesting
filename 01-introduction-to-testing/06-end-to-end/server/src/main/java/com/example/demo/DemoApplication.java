package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

@RestController
class PersonController {
	@GetMapping("/person/{id}")
	Person person(@PathVariable int id) {
		return new Person("m", "[" + id + "]");
	}
}

class Person {
	String name, surname;

	Person(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	public Person() {
	}

	public String getName() {
		return this.name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}



