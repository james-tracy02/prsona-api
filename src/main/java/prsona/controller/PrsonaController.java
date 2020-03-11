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

import prsona.model.Answer;
import prsona.model.Category;
import prsona.model.Question;
import prsona.model.Quiz;
import prsona.model.Weight;
import prsona.repository.AnswerRepository;
import prsona.repository.CategoryRepository;
import prsona.repository.QuestionRepository;
import prsona.repository.QuizRepository;
import prsona.repository.WeightRepository;

@CrossOrigin
@RestController
public class PrsonaController {
	
	@Autowired
	QuizRepository quizRepository;
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	AnswerRepository answerRepository;
	@Autowired
	WeightRepository weightRepository;
	@Autowired
	CategoryRepository categoryRepository;
	
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
		for(Question question : quiz.getQuestions()) {
			question.setQuiz(quiz);
			for(Answer answer : question.getAnswers()) {
				answer.setQuestion(question);
				for(Weight weight : answer.getWeights()) {
					weight.setAnswer(answer);
				}
			}
		}
		for(Category category : quiz.getCategories()) {
			category.setQuiz(quiz);
		}
		return quizRepository.save(quiz);
	}
	
	@PutMapping("/quizzes/{id}")
	public Quiz updateQuiz(@PathVariable int id, @RequestBody Quiz quiz) {
		for(Question question : quiz.getQuestions()) {
			question.setQuiz(quiz);
			for(Answer answer : question.getAnswers()) {
				answer.setQuestion(question);
				for(Weight weight : answer.getWeights()) {
					weight.setAnswer(answer);
				}
			}
		}
		for(Category category : quiz.getCategories()) {
			category.setQuiz(quiz);
		}
		quizRepository.deleteById(id);
		quiz.setId(id);
		return quizRepository.save(quiz);
	}
	
	@DeleteMapping("/quizzes/{id}")
	public void deleteQuiz(@PathVariable int id) {
		quizRepository.deleteById(id);
	}
}
