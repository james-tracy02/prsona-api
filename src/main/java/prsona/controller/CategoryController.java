package prsona.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import prsona.model.Category;
import prsona.repository.CategoryRepository;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryRepository categoryRepository;	
	
	@GetMapping("/categories/{id}")
	public Optional<Category> findCategoryById(@PathVariable int id) {
		return categoryRepository.findById(id);
	}
}
