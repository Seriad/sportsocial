package fr.solutec.repository;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {

}
