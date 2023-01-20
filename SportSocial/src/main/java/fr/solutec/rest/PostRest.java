package fr.solutec.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Club;
import fr.solutec.entities.Comment;
import fr.solutec.entities.Post;
import fr.solutec.entities.User;
import fr.solutec.repository.ClubRepository;
import fr.solutec.repository.CommentRepository;
import fr.solutec.repository.PostRepository;
import fr.solutec.repository.UserRepository;

@RestController
@CrossOrigin("*")
public class PostRest {

	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ClubRepository clubRepo;
	
	
	@GetMapping("club/posts/{idClub}")
	public Iterable<Post> getPostsInClub(@PathVariable Long idClub){
		Iterable<Post> Posts = postRepo.getPostClub(idClub);
		for (Post post : Posts) {
			int res = commentRepo.countCommentsFromPost(post.getIdPost());
			List<Comment> recup = commentRepo.getCommentOfPost(post.getIdPost());
			for (Comment comment : recup) {
				res = res + commentRepo.countCommentsOfComment(comment.getIdComment());
				int numberComments = commentRepo.countCommentsOfComment(comment.getIdComment());
				comment.setNumberComments(numberComments);
			}
			post.setNumberComments(res);
			int numberLikes = postRepo.countLikes(post.getIdPost());
			post.setNumberLikes(numberLikes);
		}
		return Posts;
	}
	
	@GetMapping("club/posts/checkLike/{idPost}/{idUser}")
	public Boolean checkLike(@PathVariable Long idPost, @PathVariable Long idUser) {
		Optional<Post> p = postRepo.findById(idPost);
		List<User> likePost = p.get().getLikePost();
		Boolean check= false;
		for (User user : likePost) {
			if (user.getIdUser() == idUser) {
				check = true;
			}
		}
		return check;
		
	}
	
	@PatchMapping("club/posts/like/{idPost}/{idUser}")
	public Post likePost(@PathVariable Long idPost, @PathVariable Long idUser) {
		Optional<User> u= userRepo.findById(idUser);
		Optional<Post> p = postRepo.findById(idPost);
		boolean check = checkLike(idPost, idUser);
		if (check == false) {
			p.get().getLikePost().add(u.get());	
		}
		return postRepo.save(p.get());
	}
	
	@PatchMapping("club/posts/unlike/{idPost}/{idUser}")
	public Post unlikePost(@PathVariable Long idPost, @PathVariable Long idUser){
		Optional<Post> p = postRepo.findById(idPost);
		p.get().getLikePost().removeIf(u -> u.getIdUser()==idUser);
		return postRepo.save(p.get());
	}
	
	@PostMapping("club/posts/add/{idUser}/{idClub}")
	public Post createClubPost(@PathVariable Long idUser, @PathVariable Long idClub, @RequestBody String contenu) {
		Optional<User> user = userRepo.findById(idUser);
		Optional<Club> club = clubRepo.findById(idClub);
		Post p = new Post(null, null, contenu, null, user.get(), club.get(), null, null, 0, 0);
		return postRepo.save(p);
	}
	
}
