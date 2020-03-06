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

import prsona.model.Answer;
import prsona.model.Question;
import prsona.repository.AnswerRepository;
import prsona.repository.QuestionRepository;

@CrossOrigin
@RestController
public class AnswerController {
	
	@Autowired
	AnswerRepository answerRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@GetMapping("/answers/{id}")
	public Optional<Answer> findAnswerById(@PathVariable int id) {
		return answerRepository.findById(id);
	}
	
	@GetMapping("/questions/{qid}/answers")
	public Iterable<Answer> findAnswersForQuestion(@PathVariable int qid) {
		Optional<Question> questionOpt = questionRepository.findById(qid);
		if(!questionOpt.isPresent()) {
			return new ArrayList<Answer>();
		} else {
			return questionOpt.get().getAnswers();
		}
	}
	
	@PostMapping("/questions/{qid}/answers")
	public Answer createAnswer(@PathVariable int qid, @RequestBody Answer answer) {
		Optional<Question> questionOpt = questionRepository.findById(qid);
		if(questionOpt.isPresent()) {
			Question question = questionOpt.get();
			answer.setQuestion(question);
			return answerRepository.save(answer);
		}
		return null;
	}
	
	@PutMapping("/categories/{id}")
	public Answer updateAnswer(@PathVariable int id, @RequestBody Answer answer) {
		answer.setId(id);
		return answerRepository.save(answer);
	}
	
	@DeleteMapping("/answers/{id}")
	public void deleteAnswer(@PathVariable int id) {
		answerRepository.deleteById(id);
	}
}
