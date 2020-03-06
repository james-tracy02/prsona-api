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

import prsona.model.Weight;
import prsona.model.Answer;
import prsona.repository.WeightRepository;
import prsona.repository.AnswerRepository;

@CrossOrigin
@RestController
public class WeightController {

	@Autowired
	WeightRepository weightRepository;

	@Autowired
	AnswerRepository answerRepository;

	@GetMapping("/weights/{id}")
	public Optional<Weight> findWeightById(@PathVariable int id) {
		return weightRepository.findById(id);
	}

	@GetMapping("/answers/{aid}/weights")
	public Iterable<Weight> findWeightsForAnswer(@PathVariable int aid) {
		Optional<Answer> answerOpt = answerRepository.findById(aid);
		if (!answerOpt.isPresent()) {
			return new ArrayList<Weight>();
		} else {
			return answerOpt.get().getWeights();
		}
	}

	@PostMapping("/answers/{aid}/weights")
	public Weight createWeight(@PathVariable int aid, @RequestBody Weight weight) {
		Optional<Answer> answerOpt = answerRepository.findById(aid);
		if (answerOpt.isPresent()) {
			Answer answer = answerOpt.get();
			weight.setAnswer(answer);
			return weightRepository.save(weight);
		}
		return null;
	}

	@PutMapping("/weights/{id}")
	public Weight question(@PathVariable int id, @RequestBody Weight weight) {
		weight.setId(id);
		return weightRepository.save(weight);
	}

	@DeleteMapping("/weights/{id}")
	public void deleteWeight(@PathVariable int id) {
		weightRepository.deleteById(id);
	}
}
