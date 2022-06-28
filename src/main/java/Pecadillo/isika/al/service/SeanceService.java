package Pecadillo.isika.al.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Pecadillo.isika.al.dao.SeanceDao;
import Pecadillo.isika.al.dao.UserRepository;
import Pecadillo.isika.al.model.Seance;

@Component
public class SeanceService {
	
	
	@Autowired
	SeanceDao seanceDao;
	
	@Autowired
	UserRepository userRepository;
	
	
	public Optional<List<Seance>> findSeancesByUserId(String username) { // permet de ne chercher la liste des séances qu'a partir du UserId
		
		
		return seanceDao.findAllByUser(userRepository.findByUsername(username).get());

	}

}
