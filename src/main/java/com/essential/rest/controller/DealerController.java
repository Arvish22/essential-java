package com.essential.rest.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.essential.domain.CategoryDomain;
import com.essential.firebase.service.FirebaseService;

@RestController
public class DealerController {		
	
	@Autowired
	FirebaseService service;

	@GetMapping("/dealers")
	public List<CategoryDomain> getDealers(){
		try {
			return service.getAll();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
//	@PostMapping("/dealers")
//	@GetMapping("/dealers/{id}")
}
