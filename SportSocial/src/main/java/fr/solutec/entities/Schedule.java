package fr.solutec.entities;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Entity @Data
public class Schedule {
	@Id @GeneratedValue
	private Long idSchedule;
	private String nameActivity;
	private Timestamp dateStar;
	private Timestamp dateEnd;
	@ManyToOne
	private Session sessionSchedule;
	@OneToOne
	private User userSchedule;
	
}
