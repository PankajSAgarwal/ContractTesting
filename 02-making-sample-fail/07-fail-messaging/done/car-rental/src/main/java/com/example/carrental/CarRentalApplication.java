package com.example.carrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableBinding(Sink.class)
public class CarRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApplication.class, args);
	}
}

@Component
class FraudListener {

	String name;

	@StreamListener(Sink.INPUT)
	public void fraud(Fraud fraud) {
		System.out.println("Got the message [" + fraud + "]");
		this.name = fraud.surname;
	}
}

class Fraud {
	public String surname;

	public Fraud(String surname) {
		this.surname = surname;
	}

	public Fraud() {
	}

	@Override public String toString() {
		return "Fraud{" + "name='" + this.surname + '\'' + '}';
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
