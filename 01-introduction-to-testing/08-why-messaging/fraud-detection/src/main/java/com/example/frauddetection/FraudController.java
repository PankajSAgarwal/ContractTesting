package com.example.frauddetection;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mgrzejszczak.
 */
// (1) - Write the controller
@RestController
public class FraudController {

	// (2) - messaging
	private final Source source;

	public FraudController(Source source) {
		this.source = source;
	}

	// (2) - we're sending a message to a destination `frauds` - consumer expects `fraud`
	@PostMapping("/message")
	void message() {
		this.source.output().send(MessageBuilder.withPayload(new Fraud("Long")).build());
	}
}

class Fraud {
	String surname;

	public Fraud(String surname) {
		this.surname = surname;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}