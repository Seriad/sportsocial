package fr.solutec.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
}
