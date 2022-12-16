package fr.solutec.repository;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.UserActivity;

public interface UserActivityRepository extends CrudRepository<UserActivity, Long>{

}
