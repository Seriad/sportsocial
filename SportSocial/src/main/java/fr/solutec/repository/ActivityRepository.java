package fr.solutec.repository;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Activity;

public interface ActivityRepository extends CrudRepository<Activity, Long>{

}
