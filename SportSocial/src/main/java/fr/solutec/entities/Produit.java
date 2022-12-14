package fr.solutec.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Entity @Data
public class Produit {

	@Id @GeneratedValue
	private Long idProduit;
	
	private String descriptionProduit;
	private Double prixTokenProduit;
	@ManyToOne
	private Image imageProduit;
}
