package com.example.integrationtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

@Configuration
class PersonConfiguration {

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	PersonClient personClient() {
		return new HttpPersonClient(restTemplate());
	}

	@Bean
	PersonService personService(){

		return new PersonService(personClient());
	}
}
class PersonService {
	private PersonClient personClient;

	public PersonService(PersonClient personClient) {
		this.personClient = personClient;
	}

	List<Person> people() {
		List<Person> people = new ArrayList<>();

		people.add(this.personClient.person(1));
		people.add(this.personClient.person(2));

		return people;
	}
}

interface PersonClient {
	Person person(int id);
}

class HttpPersonClient implements PersonClient {
	private final RestTemplate restTemplate;

	public HttpPersonClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}


	@Override
	public Person person(int id) {
		return this.restTemplate.getForObject("http://localhost:8081/person/{id}", Person.class, id);
	}
}
class Person {
	String name, surname;

	public Person(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}
}
