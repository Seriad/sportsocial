package fr.solutec.repository;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Sport;

public interface SportRepository extends CrudRepository<Sport, Long> {

}
