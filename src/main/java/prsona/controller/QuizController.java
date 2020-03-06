package prsona.controller;

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

import prsona.model.Quiz;
import prsona.repository.QuizRepository;

@CrossOrigin
@RestController
public class QuizController {
	
	@Autowired
	QuizRepository quizRepository;	
	
	@GetMapping("/quizzes/{id}")
	public Optional<Quiz> findQuizById(@PathVariable int id) {
		return quizRepository.findById(id);
	}
	
	@GetMapping("/quizzes")
	public Iterable<Quiz> findAllQuizzes() {
		return quizRepository.findAll();
	}
	
	@PostMapping("/quizzes")
	public Quiz createQuiz(@RequestBody Quiz quiz) {
		return quizRepository.save(quiz);
	}
	
	@PutMapping("/quizzes/{id}")
	public Quiz updateQuiz(@PathVariable int id, @RequestBody Quiz quiz) {
		quiz.setId(id);
		return quizRepository.save(quiz);
	}
	
	@DeleteMapping("/quizzes/{id}")
	public void deleteQuiz(@PathVariable int id) {
		quizRepository.deleteById(id);
	}
}
