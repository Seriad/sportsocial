package fr.solutec.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserActivityPK implements  Serializable{
	
	private User userFK;
	private Activity activityFK;
	
}
