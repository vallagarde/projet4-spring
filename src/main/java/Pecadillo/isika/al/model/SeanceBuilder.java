package Pecadillo.isika.al.model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Pecadillo.isika.al.dao.PriseDao;
import Pecadillo.isika.al.dao.UserRepository;
import Pecadillo.isika.al.exception.UserNotFindException;
import Pecadillo.isika.al.payload.request.SeanceRequest;

@Component
public class SeanceBuilder {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PriseDao priseJpa;
	
	@Autowired 
	PrisesBuilder prisesBuilder;
	
	

	public Seance SeanceBuild(SeanceRequest seance) throws UserNotFindException {

		Seance newSeance = new Seance();
		newSeance.setDescription(seance.getDescription());
		newSeance.setMeteoId( seance.getMeteoId());
		newSeance.setMeteoIndex(seance.getMeteoIndex());
		//newSeance.setPrises(seance.getPrises());
		newSeance.setTitre(seance.getTitre());
		newSeance.setLatitude(seance.getLatitude());
		newSeance.setLongitude(seance.getLongitude());
		
		
		Optional<User> gotUser = userRepository.findByEmail(seance.getUserEmail());
		
		try {
		newSeance.setUser(gotUser.get());
		}catch(Exception e) {
			throw new UserNotFindException();
		}

		return newSeance;
	}

}
