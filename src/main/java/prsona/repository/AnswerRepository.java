package prsona.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import prsona.model.Answer;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Integer> { }
