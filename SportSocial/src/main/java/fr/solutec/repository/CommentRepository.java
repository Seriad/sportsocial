package fr.solutec.repository;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
