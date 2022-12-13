package fr.solutec.repository;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Image;

public interface ImageRepository extends CrudRepository<Image, Long>{

}
