package fr.solutec.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Achat;
import fr.solutec.entities.Exchange;
import fr.solutec.entities.Produit;
import fr.solutec.entities.User;
import fr.solutec.repository.AchatRepository;
import fr.solutec.repository.ExchangeRepository;
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
	@Autowired
	private ExchangeRepository exchangeRepo;

	
	
	@GetMapping("boutique/achat/{idUser}/{idProduit}")
	private Optional<Achat> achatProduit(@PathVariable Long idUser,@PathVariable Long idProduit) {
		Optional<Achat> a = Optional.of(new Achat(null,null,false));
		Optional<User> u = userRepo.findById(idUser);
		Optional<Produit> p =  produitRepo.findById(idProduit);
		a.get().setUserFK(u.get());
		a.get().setProduitFK(p.get());
		if (u.get().getToken()>=p.get().getPrixTokenProduit()) {
			u.get().setToken(u.get().getToken()-p.get().getPrixTokenProduit());
			a.get().setValidation(true);
			achatRepo.save(a.get());
			u.get().getInventaire().add(p.get().getImageProduit().getIdImage());
			userRepo.save(u.get());
			return Optional.of(a.get());
			
		}
		return Optional.of(a.get());
	}
	
	@GetMapping("boutique/achat/euro/{idUser}/{montantEuro}")
	private boolean achatTokenavecEuro(@PathVariable Long idUser, @PathVariable int montantEuro) {
		Optional<User> u = userRepo.findById(idUser);
		if(montantEuro>0) {
		u.get().setToken(u.get().getToken() + montantEuro*3);
		userRepo.save(u.get());
		return true;
		
	}
		return false;
	}
	
	@GetMapping("boutique/achatToken/{idUser}/{montantToken}")
	private boolean achatToken(@PathVariable Long idUser, @PathVariable int montantToken) {
		Optional<User> u = userRepo.findById(idUser);
		if(montantToken>0) {
		u.get().setToken(u.get().getToken() +montantToken);
		userRepo.save(u.get());
		return true;
		
	}else {
		return false;
	}}
	
	@GetMapping("boutique/echangeToken/{idUser}/{idReceiver}/{montantToken}")
	private boolean echangeToken(@PathVariable Long idUser, @PathVariable Long idReceiver, @PathVariable int montantToken) {
		Exchange e = new Exchange();
		Optional<User> u = userRepo.findById(idUser);
		Optional<User> receiver = userRepo.findById(idReceiver);
		if(montantToken>0) {
			if(montantToken <= u.get().getToken()) {
				receiver.get().setToken(receiver.get().getToken() + montantToken);
				u.get().setToken(u.get().getToken() - montantToken);
				userRepo.save(u.get());
				userRepo.save(receiver.get());
				e.setApplicant(u.get());
				e.setReceiver(receiver.get());
				e.setMontantToken(montantToken);
				e.setNotification(e.getApplicant().getLoginUser() + " vous a envoyé " + e.getMontantToken() + " Tokens");
				exchangeRepo.save(e);
				return true;
			}
		}
		return false;
	}
	
	

}
