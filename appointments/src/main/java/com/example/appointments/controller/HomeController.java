package com.example.appointments.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String showHomePage() {
		// Logic to display the home page
		return "index"; // Return the view name or redirect to the home page
	}
}