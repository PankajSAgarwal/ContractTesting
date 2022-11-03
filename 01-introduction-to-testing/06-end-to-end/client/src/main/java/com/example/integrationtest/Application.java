package com.example.integrationtest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
	PersonClient personClient(@Value("${person.server.url:localhost}") String serverUrl,
			@Value("${person.server.port:8081}") int serverPort) {
		return new HttpPersonClient(restTemplate(), serverUrl + ":" + serverPort);
	}

}

@RestController
class PersonController {

	private final PersonService personService;

	PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping("/person")
	List<Person> people() {
		return this.personService.people();
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
	private final String serverUrl;

	HttpPersonClient(RestTemplate restTemplate, String serverUrl) {
		this.restTemplate = restTemplate;
		this.serverUrl = serverUrl;
	}

	@Override
	public Person person(int id) {
		return this.restTemplate.getForObject("http://" + this.serverUrl + "/person/{id}",
				Person.class, id);
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
