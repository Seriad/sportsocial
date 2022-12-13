package fr.solutec.repository;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Address;

public interface AddressRepository extends CrudRepository<Address, Long>{

}
