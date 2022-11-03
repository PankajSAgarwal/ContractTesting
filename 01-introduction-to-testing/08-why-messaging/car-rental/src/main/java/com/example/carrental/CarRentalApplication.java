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

// FOR MANUAL TESTS

@Component
class FraudListener {

	String name;

	@StreamListener(Sink.INPUT)
	public void fraud(Fraud fraud) {
		System.out.println("Got the message [" + fraud + "]");
		this.name = fraud.name;
	}
}

class Fraud {
	public String name;

	public Fraud(String name) {
		this.name = name;
	}

	public Fraud() {
	}

	@Override public String toString() {
		return "Fraud{" + "name='" + this.name + '\'' + '}';
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
