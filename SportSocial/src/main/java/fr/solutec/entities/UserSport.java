package fr.solutec.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Entity @Data @IdClass(UserSportPK.class)
public class UserSport {
	
	@Id @ManyToOne
	private User userFK;
	@Id @ManyToOne

	private Sport sportFK;

}
