package fr.solutec.repository;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{

}