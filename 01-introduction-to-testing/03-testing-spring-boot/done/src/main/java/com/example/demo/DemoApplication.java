package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

@Configuration
class PersonConfiguration {
	@Bean
	PersonFetcher fetcher() {
		return new PersonFetcher();
	}

	@Bean
	PersonPrinter printer(PersonFetcher fetcher) {
		return new PersonPrinter(fetcher);
	}
}

class PersonPrinter {
	private final PersonFetcher personFetcher;

	PersonPrinter(PersonFetcher personFetcher) {
		this.personFetcher = personFetcher;
	}

	String print() {
		return this.personFetcher.fetchPeople().stream()
				.map(person -> person.name)
				.collect(Collectors.joining("\n"));
	}
}

class PersonFetcher {
	List<Person> fetchPeople() {
		return Arrays.asList(new Person("marcin", "g"),
				new Person("john", "d"));
	}
}

class Person {
	String name, surname;

	Person(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}
}




