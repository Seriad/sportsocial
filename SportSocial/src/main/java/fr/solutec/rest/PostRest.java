package fr.solutec.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Post;
import fr.solutec.repository.PostRepository;

@RestController
@CrossOrigin("*")
public class PostRest {

	@Autowired
	private PostRepository postRepo;
	
	
	@GetMapping("club/posts/{idClub}")
	public Iterable<Post> getPostsInClub(@PathVariable Long idClub){
		return postRepo.getPostClub(idClub);
	}
}
