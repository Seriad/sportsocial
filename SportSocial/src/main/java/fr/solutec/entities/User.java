package fr.solutec.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Entity @Data
public class User {
	@Id @GeneratedValue
	private Long idUser;
	private String lastNameUser;
	private String firstNameUser;
	private Date birthDateUser;
	@Column(unique=true)
	private String loginUser;
	private String passwordUser;
	private Boolean coachUser;
	@ManyToOne
	private Address addressUser;
	@ManyToOne
	private Image imageUser;
	private double token;
	
	
	
}
