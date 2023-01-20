package fr.solutec.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Entity @Data @IdClass(UserActivityConstraint.class)
public class UserActivity {
	
	@Id @ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	@Id @ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Activity activity;
}
