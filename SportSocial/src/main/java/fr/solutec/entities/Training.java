package fr.solutec.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Entity @Data
public class Training {
	
	@Id @GeneratedValue
	private Long idTraining;
	private String memo;
	@ManyToOne
	private User author;
	@ManyToOne
	private Sport trainingSport;
	
	
	


}
