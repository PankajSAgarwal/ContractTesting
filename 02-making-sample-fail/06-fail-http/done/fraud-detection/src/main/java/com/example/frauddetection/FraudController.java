package com.example.frauddetection;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mgrzejszczak.
 */
// (1) - Write the controller
@RestController
public class FraudController {

	// (1) - Make a typo (on the consumer side it will be `/fraud`
	@GetMapping("/frauds")
	ResponseEntity<List<String>> frauds() {
		return ResponseEntity.status(200).body(Arrays.asList("marcin", "josh"));
	}
}