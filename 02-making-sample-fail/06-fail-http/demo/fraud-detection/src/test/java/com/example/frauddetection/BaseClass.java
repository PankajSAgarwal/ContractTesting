package com.example.frauddetection;

import org.junit.Before;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import static org.codehaus.groovy.tools.shell.util.Logger.io;

public class BaseClass {
	@Before
	public void setup(){
		RestAssuredMockMvc.standaloneSetup(new FraudController());

}
}
