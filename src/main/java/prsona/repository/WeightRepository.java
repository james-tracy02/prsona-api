package prsona.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import prsona.model.Weight;

@Repository
public interface WeightRepository extends CrudRepository<Weight, Integer> { }
