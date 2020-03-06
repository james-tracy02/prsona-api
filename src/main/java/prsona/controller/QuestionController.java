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

import prsona.model.Question;
import prsona.model.Quiz;
import prsona.repository.QuestionRepository;
import prsona.repository.QuizRepository;

@CrossOrigin
@RestController
public class QuestionController {
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	QuizRepository quizRepository;
	
	@GetMapping("/questions/{id}")
	public Optional<Question> findQuestionById(@PathVariable int id) {
		return questionRepository.findById(id);
	}
	
	@GetMapping("/quizzes/{qid}/questions")
	public Iterable<Question> findQuestionsForQuiz(@PathVariable int qid) {
		Optional<Quiz> quizOpt = quizRepository.findById(qid);
		if(!quizOpt.isPresent()) {
			return new ArrayList<Question>();
		} else {
			return quizOpt.get().getQuestions();
		}
	}
	
	@PostMapping("/quizzes/{qid}/questions")
	public Question createQuestion(@PathVariable int qid, @RequestBody Question question) {
		Optional<Quiz> quizOpt = quizRepository.findById(qid);
		if(quizOpt.isPresent()) {
			Quiz quiz = quizOpt.get();
			question.setQuiz(quiz);
			return questionRepository.save(question);
		}
		return null;
	}
	
	@PutMapping("/questions/{id}")
	public Question updateQuestion(@PathVariable int id, @RequestBody Question question) {
		question.setId(id);
		return questionRepository.save(question);
	}
	
	@DeleteMapping("/questions/{id}")
	public void deleteQuestion(@PathVariable int id) {
		questionRepository.deleteById(id);
	}
}
