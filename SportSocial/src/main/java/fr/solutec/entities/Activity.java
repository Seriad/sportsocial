package fr.solutec.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Entity @Data
public class Activity {
	
	@Id @GeneratedValue
	private Long idActivity;
	
	private float distance;
	private Timestamp dateStart;
	private Timestamp dateEnd;
	private String descActivity;
	@ManyToOne	
	private Address addressActivity;
	@ManyToOne
	private Sport sportActivity;
	@ManyToOne
	private Schedule scheduleActivity;
	

}
