package fr.solutec.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor @AllArgsConstructor
@Entity @Data
public class Sport {
	@Id @GeneratedValue
	private Long idSport;
	private String nameSport;
	@OneToOne
	private Image imageSport;
	
}
