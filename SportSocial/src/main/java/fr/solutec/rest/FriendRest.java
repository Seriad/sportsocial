package fr.solutec.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Friend;
import fr.solutec.entities.User;
import fr.solutec.repository.FriendRepository;
import fr.solutec.repository.UserRepository;

@RestController @CrossOrigin("*")
public class FriendRest {
	
	 @Autowired
	    private FriendRepository friendRepos;
	    @Autowired
	    private UserRepository userRepos;

	 

	    @PostMapping("friend/{idUser}") //demande
	    private Friend createFriendship (@RequestBody User a, @PathVariable Long idUser) {
	    	Optional<User> u = userRepos.findById(idUser);
	    	
	    	Friend f = new Friend (null, u.get(), a, false);
	        return friendRepos.save(f);
	    }
	 

	    @PatchMapping("friend/accept/{idFriend}") //accepter la demande
	    private Friend acceptFriendship (@RequestBody Friend f, @PathVariable Long idFriend ) {
	        f.setIdFriend(idFriend);
	        return friendRepos.save(f);    
	    }
	 

	    @DeleteMapping("friend/refuse/{idFriend}") //Refuser ou supprimer une amitié
	    private boolean refuseFriendship (@PathVariable Long idFriend) {
	        Optional<Friend> f = friendRepos.findById(idFriend);
	        if (f.isPresent()) {
	            friendRepos.deleteById(idFriend);
	            return true;
	        }else {
	            return false;
	        }
	    }
	       


	    @GetMapping("friend/receiver/{idReceiver}") //Voir ses amis acceptés
	    private List<User> MyFriendship(@PathVariable Long idReceiver){
	        List<User> friends = new ArrayList <>();
	        List<Friend> recup = friendRepos.getMyFriends(idReceiver);
	        Optional<User> u = userRepos.findById(idReceiver);
	        if(u.isPresent()) {
	            for (Friend friend : recup) {
	                if(!friend.getApplicant().equals(u.get())) {
	                    friends.add(friend.getApplicant());
	                }
	                if(!friend.getReceiver().equals(u.get())) {
	                    friends.add(friend.getReceiver());
	                }
	            }
	        }
	        return friends;
	    }
	    
	    @GetMapping("notfriend/receiver/{idReceiver}") //Les demandes d'amis
	    private List<User> MyNotFriendship(@PathVariable Long idReceiver){
	        List<User> friends = new ArrayList <>();
	        List<Friend> recup = friendRepos.getMyFriendRequest(idReceiver);
	        Optional<User> u = userRepos.findById(idReceiver);
	        if(u.isPresent()) {
	            for (Friend friend : recup) {
	                if(!friend.getApplicant().equals(u.get())) {
	                    friends.add(friend.getApplicant());
	                }
	                if(!friend.getReceiver().equals(u.get())) {
	                    friends.add(friend.getReceiver());
	                }
	            }
	        }
	        return friends;
	    }
	    
	    @GetMapping("notfriend/receiverdelete/{idReceiver}") //Les demandes d'amis
	    private List<User> MyNotFriendshipDelete(@PathVariable Long idReceiver){
	        List<User> friends = new ArrayList <>();
	        List<Friend> recup = friendRepos.getMyFriendRequestDelete(idReceiver);
	        Optional<User> u = userRepos.findById(idReceiver);
	        if(u.isPresent()) {
	            for (Friend friend : recup) {
	                if(!friend.getApplicant().equals(u.get())) {
	                    friends.add(friend.getApplicant());
	                }
	                if(!friend.getReceiver().equals(u.get())) {
	                    friends.add(friend.getReceiver());
	                }
	            }
	        }
	        return friends;
	    }
	    
	    @GetMapping("nofriend/receiver/{idReceiver}") //Les non amis
	    private List<User> NotFriendship(@PathVariable Long idReceiver){
	        List<User> friends = new ArrayList <>();
	        List<Friend> recup = friendRepos.getMyFriendRequest(idReceiver);
	        Optional<User> u = userRepos.findById(idReceiver);
	        if(!(u.isPresent())) {
	            for (Friend friend : recup) {
	                if(!friend.getApplicant().equals(u.get())) {
	                    friends.add(friend.getApplicant());
	                }
	                if(!friend.getReceiver().equals(u.get())) {
	                    friends.add(friend.getReceiver());
	                }
	            }
	        }
	        return friends;
	    }
	    
	    
	    @PatchMapping("update/{idApplicant}/{idReceiver}")
	    private boolean Updatelisteamis(@PathVariable Long idReceiver, @PathVariable Long idApplicant) {
	    	friendRepos.updateMyFriends(idApplicant, idReceiver);
	    	return true;
	    }
	    
	    @GetMapping("select/{idApplicant}/{idReceiver}") // sélectionner idFriend si demander d'amitié en cours 
	    private Optional<Friend> selectFriendRelation (@PathVariable Long idReceiver, @PathVariable Long idApplicant){
	    	return friendRepos.SelectRelationMyFriends(idApplicant, idReceiver);
	    }
	    
	    @GetMapping("selectaccepted/{idApplicant}/{idReceiver}") // selectionner l'idFriend si ami 
	    private Optional<Friend> selectFriendRelationAccepted (@PathVariable Long idReceiver, @PathVariable Long idApplicant){
	    	return friendRepos.SelectRelationMyFriendsAccepted(idApplicant, idReceiver);
	    }
	    
	   

	    // Voir les personnes avec lesquelles on n'est pas ami 
	    @GetMapping("nonfriend/{idUser}") // Obtenir les users non amis
		public List<User> usersNonFriends(@PathVariable Long idUser){
			return userRepos.getNonFriends(idUser);
		}
        
	    @GetMapping("askingFriend/{idReceiver}")
	    private List<Friend> SelectPeopleWantingToBeFriend (@PathVariable Long idReceiver){
	    	return friendRepos.SelectMydemand( idReceiver);
	    	
	    }
}
