package fr.solutec.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserSportPK implements  Serializable{

	private User userFK;
	private Sport sportFK;
	
}
