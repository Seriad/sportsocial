package fr.solutec.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Image;

public interface ImageRepository extends CrudRepository<Image, Long>{
	
	public Optional<Image> findByNameImage (String nameImage);

}
