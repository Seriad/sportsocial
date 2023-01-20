package fr.solutec.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Exchange;

public interface ExchangeRepository extends CrudRepository<Exchange, Long> {
	
	public List<Exchange> findByReceiverIdUser(Long id);

}
