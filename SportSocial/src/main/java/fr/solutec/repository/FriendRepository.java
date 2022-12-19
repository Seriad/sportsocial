package fr.solutec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Friend;

public interface FriendRepository extends CrudRepository<Friend, Long> {
    @Query("SELECT f FROM Friend f WHERE (f.applicant.id = ?1 OR f.receiver.id = ?1) AND accept = true")
    List<Friend> getMyFriends (Long idUser);
    
 

    @Query("SELECT f FROM Friend f WHERE (f.applicant.id = ?1 OR f.receiver.id = ?1) AND accept = false")
    List<Friend> getMyFriendRequest (Long idUser);
    
    
    @Query("SELECT f FROM Friend f WHERE (f.applicant.id = ?1 OR f.receiver.id = ?1) AND accept = false OR (f.receiver.id = ?1 OR f.applicant.id = ?1) AND accept = false")
    List<Friend> deleteMyFriends (Long idApplicant, Long idReceiver);
    
    @Query("UPDATE Friend f SET f.accept=true WHERE (f.applicant.id = ?1 OR f.receiver.id = ?1) AND accept = false OR (f.receiver.id = ?1 OR f.applicant.id = ?1) AND accept = false")
    List<Friend> updateMyFriends (Long idApplicant, Long idReceiver);


    @Query("SELECT f FROM Friend f WHERE (f.applicant.id = ?1 OR f.receiver.id = ?1) AND accept = false OR (f.receiver.id = ?1 OR f.applicant.id = ?1) AND accept = false")
    List<Friend> SelectRelationMyFriends (Long idApplicant, Long idReceiver);
}
