package fr.solutec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Produit;

public interface ProduitRepository extends CrudRepository<Produit, Long> {
	
	
	@Query("SELECT p FROM Produit p WHERE descriptionProduit LIKE %?1%")
	public  List<Produit> getProduitByDescriptif(String description);
	
	
	

}
