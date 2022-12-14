package fr.solutec.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Entity @Data @IdClass(UserActivityConstraint.class)
public class UserActivity {
	
	@Id @ManyToOne
	private User user;
	@Id @ManyToOne
	private Activity activity;
}
