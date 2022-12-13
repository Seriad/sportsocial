package fr.solutec.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Entity @Data
public class Address {
	
	@Id @GeneratedValue
	private Long idAddress;
	private String streetAddress;
	private String zipCodeAddress;
	private String cityAddress;

}
