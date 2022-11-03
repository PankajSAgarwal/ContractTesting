package com.example.integrationtest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

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
	PersonService personService(PersonClient client) {
		return new PersonService(client);
	}

	@Bean
	PersonClient personClient() {
		return new HttpPersonClient(restTemplate());
	}

}

class PersonService {

	private final PersonClient personClient;

	PersonService(PersonClient personClient) {
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

	HttpPersonClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public Person person(int id) {
		return this.restTemplate.getForObject("http://localhost:8081/person/{id}",
				Person.class, id);
	}
}

class Person {
	String name, surname;

	public Person(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	public Person() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}