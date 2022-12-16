package fr.solutec.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
@Entity
public class Friend {
	@Id @GeneratedValue
    private Long idFriend;
    @ManyToOne
    private User applicant;
    @ManyToOne
    private User receiver;
    private boolean accept;
}

