package fr.solutec.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.NoArgsConstructor;

@Embeddable

public class UserEventPK implements Serializable{

	private Long userFK;
	private Long eventFK;
}
