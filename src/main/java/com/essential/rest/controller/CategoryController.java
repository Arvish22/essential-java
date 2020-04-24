package com.essential.rest.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.essential.crawler.config.HtmlCrawler;
import com.essential.domain.CategoryDomain;
import com.essential.firebase.service.FirebaseService;

@RestController
public class CategoryController {
	
	@Autowired
	FirebaseService service;
	
	@Autowired
	HtmlCrawler crawler;
	
	@GetMapping("/category")
	public List<CategoryDomain> get() {
//			CategoryDomain cd = new CategoryDomain();
//			ArrayList<DealerDomain> arrDD = new ArrayList<DealerDomain>();
//			arrDD.add(new DealerDomain());
//			cd.setDealer(arrDD);
//			return cd;
		try {
			return service.getAll();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@GetMapping("/category/{id}")
	public CategoryDomain getOne(@PathVariable String id) {
//			CategoryDomain cd = new CategoryDomain();
//			ArrayList<DealerDomain> arrDD = new ArrayList<DealerDomain>();
//			arrDD.add(new DealerDomain());
//			cd.setDealer(arrDD);
//			return cd;
		try {
			return service.getUserDetails(id);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@PostMapping("/category")
	public String save(@RequestBody CategoryDomain category) {
		System.out.println(category.getType());
			try {
				service.saveUserDetails(category);
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "Saved";
	}
}
