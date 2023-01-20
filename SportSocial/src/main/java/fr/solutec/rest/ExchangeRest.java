package fr.solutec.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Exchange;
import fr.solutec.repository.ExchangeRepository;

@RestController
@CrossOrigin("*")
public class ExchangeRest {
	@Autowired
	private ExchangeRepository exchangeRepo;
	
	@GetMapping("echange/{idReceiver}")
	private List<Exchange> showExchange (@PathVariable Long idReceiver){
		List<Exchange> e = exchangeRepo.findByReceiverIdUser(idReceiver);
		return e;		
	}

}
