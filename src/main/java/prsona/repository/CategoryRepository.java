package prsona.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import prsona.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> { }
