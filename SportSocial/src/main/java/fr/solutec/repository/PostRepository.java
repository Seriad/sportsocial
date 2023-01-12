package fr.solutec.repository;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Post;

public interface PostRepository extends CrudRepository<Post, Long>{

}
