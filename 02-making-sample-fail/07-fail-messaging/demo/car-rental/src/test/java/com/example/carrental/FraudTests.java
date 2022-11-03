package com.example.carrental;

import org.assertj.core.api.BDDAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarRentalApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class FraudTests {

	@Autowired FraudListener fraudListener;
	@Autowired Sink sink;

	@Before
	public void setup() {
		this.fraudListener.name = "";
	}

	// 1 - the same pojo for serialization and deserialization
	// Run the app with real rabbit against the producer
	@Test
	public void should_store_info_about_fraud() {
		Fraud fraud = new Fraud("marcin");

		this.sink.input().send(MessageBuilder.withPayload(fraud).build());

		BDDAssertions.then(this.fraudListener.name).isEqualTo("marcin");
	}
}
