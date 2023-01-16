package fr.solutec.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.swing.text.html.parser.Element;

import org.hibernate.annotations.CreationTimestamp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data

public class Post {
	
	@Id
	@GeneratedValue
	private Long idPost;
	@CreationTimestamp
	private Timestamp dateCreationPost;
	@Column(columnDefinition="TEXT")
	private String contentPost;
	@ManyToOne
	private Image imagePost;
	@ManyToOne
	private User createurPost;
	@ManyToOne
	private Club clubPost;
	@ManyToMany
	private List<User> likePost = new ArrayList<User>();
	@ManyToMany
	private List<Comment> commentsPost = new ArrayList<Comment>();
	
}
