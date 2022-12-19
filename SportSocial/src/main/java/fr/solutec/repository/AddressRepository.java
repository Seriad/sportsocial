package fr.solutec.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Address;

public interface AddressRepository extends CrudRepository<Address, Long>{
@Query("SELECT a FROM Address a INNER JOIN User u WHERE u.addressUser.idAddress = ?1 ")
public Address findByUser(Long idAddress);
}
