package fr.solutec.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data

public class Club {
		@Id
		@GeneratedValue
		private Long idClub;
		private String titleClub;
		private String descriptionClub;
		@ManyToOne
		private Image imageClub;
		@ManyToOne
		private Sport sportClub;
		@ManyToOne
		private User createur;
		@ManyToMany
		private List<User> admin = new ArrayList<User>();
		@ManyToMany
		private List<User> membres = new ArrayList<User>();
}
