package Pecadillo.isika.al.service;

import org.springframework.stereotype.Component;

import Pecadillo.isika.al.model.User;
import Pecadillo.isika.al.payload.request.UserUpdateRequest;

@Component
public class UserMapper {
	
	
	public User UserMap(User user, UserUpdateRequest userUpdate) {
		
		user.setAdresse(userUpdate.getAdresse());
		user.setCodePostal(userUpdate.getCodePostal());
		user.setPrenom(userUpdate.getPrenom());
		user.setNom(userUpdate.getNom());
		user.setPays(userUpdate.getPays());
		user.setVille(userUpdate.getVille());
		
		return user;
		
	}
	

}
