package fr.solutec.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Achat;
import fr.solutec.entities.Produit;
import fr.solutec.entities.User;
import fr.solutec.repository.AchatRepository;
import fr.solutec.repository.ProduitRepository;
import fr.solutec.repository.UserRepository;

@RestController @CrossOrigin("*")
public class AchatRest {
	@Autowired
	private AchatRepository achatRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ProduitRepository produitRepo;
	
	
	@PatchMapping("boutique/achat/{idUser}/{idProduit}")
	private Optional<Achat> achatProduit(@PathVariable Long idUser,@PathVariable Long idProduit) {
		Optional<Achat> a = Optional.of(new Achat(null,null,false));
		Optional<User> u = userRepo.findById(idUser);
		Optional<Produit> p =  produitRepo.findById(idProduit);
		a.get().setUserFK(u.get());
		a.get().setProduitFK(p.get());
		if (u.get().getToken()>p.get().getPrixTokenProduit()) {
			u.get().setToken(u.get().getToken()-p.get().getPrixTokenProduit());
			a.get().setValidation(true);
			achatRepo.save(a.get());
			return Optional.of(a.get());
			
		}
		return Optional.of(a.get());
	}
	

}
