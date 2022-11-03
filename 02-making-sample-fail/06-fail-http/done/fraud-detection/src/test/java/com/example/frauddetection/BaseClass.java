package com.example.frauddetection;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

/**
 * Created by mgrzejszczak.
 */
public class BaseClass {

	@Before
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(new FraudController());
	}
}
