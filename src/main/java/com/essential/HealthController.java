package com.essential;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
	
	@GetMapping
	public String heartRate() {
		return "I am Alive";
	}
}
