package prsona.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import prsona.model.Quiz;

@Repository
public interface QuizRepository extends CrudRepository<Quiz, Integer> { }
