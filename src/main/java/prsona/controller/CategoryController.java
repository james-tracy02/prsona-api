package prsona.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import prsona.model.Category;
import prsona.model.Quiz;
import prsona.repository.CategoryRepository;
import prsona.repository.QuizRepository;

@CrossOrigin
@RestController
public class CategoryController {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	QuizRepository quizRepository;
	
	@GetMapping("/categories/{id}")
	public Optional<Category> findCategoryById(@PathVariable int id) {
		return categoryRepository.findById(id);
	}
	
	@GetMapping("/quizzes/{qid}/categories")
	public Iterable<Category> findCategoriesForQuiz(@PathVariable int qid) {
		Optional<Quiz> quizOpt = quizRepository.findById(qid);
		if(!quizOpt.isPresent()) {
			return new ArrayList<Category>();
		} else {
			return quizOpt.get().getCategories();
		}
	}
	
	@PostMapping("/quizzes/{qid}/categories")
	public Category createCategory(@PathVariable int qid, @RequestBody Category category) {
		Optional<Quiz> quizOpt = quizRepository.findById(qid);
		if(quizOpt.isPresent()) {
			Quiz quiz = quizOpt.get();
			category.setQuiz(quiz);
			return categoryRepository.save(category);
		}
		return null;
	}
	
	@PutMapping("/categories/{id}")
	public Category updateCategory(@PathVariable int id, @RequestBody Category category) {
		category.setId(id);
		return categoryRepository.save(category);
	}
	
	@DeleteMapping("/categories/{id}")
	public void deleteCategory(@PathVariable int id) {
		categoryRepository.deleteById(id);
	}
}
