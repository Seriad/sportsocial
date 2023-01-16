package fr.solutec.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Comment;
import fr.solutec.repository.CommentRepository;

@RestController
@CrossOrigin("*")
public class CommentRest {

	@Autowired
	private CommentRepository commentRepo;
	
	@GetMapping("club/posts/comments/{idPost}")
	public Iterable<Comment> getCommentsInPost(@PathVariable Long idPost){
		return commentRepo.getCommentOfPost(idPost);
	}
	
	@GetMapping("club/posts/comments/count/{idPost}")
	public int getNumberCommentsOfPost(@PathVariable Long idPost) {
		int res = commentRepo.countCommentsFromPost(idPost);
		List<Comment> recup = commentRepo.getCommentOfPost(idPost);
		for (Comment comment : recup) {
			res = res + commentRepo.countCommentsOfComment(comment.getIdComment());
		}
		return res ;
	}
	
	
}
