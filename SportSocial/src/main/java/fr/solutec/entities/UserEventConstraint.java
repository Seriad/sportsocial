package fr.solutec.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.NoArgsConstructor;

@Embeddable

public class UserEventConstraint implements Serializable{

	private Long user;
	private Long event;
}
