package com.example.frauddetection;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by mgrzejszczak.
 */
// Messaging base class
@RunWith(SpringRunner.class)
@AutoConfigureMessageVerifier
@SpringBootTest(classes = FraudDetectionApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class BaseClass {

	@Autowired FraudController controller;

	public void triggerMethod() {
		this.controller.message();
	}
}
