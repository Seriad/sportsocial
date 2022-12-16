package fr.solutec.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.UserActivity;

public interface UserActivityRepository extends CrudRepository<UserActivity, Long>{

	List<UserActivity>findByUserIdUser(Long iduser);
}
