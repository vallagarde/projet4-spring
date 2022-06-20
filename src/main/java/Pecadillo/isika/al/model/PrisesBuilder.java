package Pecadillo.isika.al.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Pecadillo.isika.al.dao.PriseDao;
import Pecadillo.isika.al.payload.request.SeanceRequest;

@Component
public class PrisesBuilder {
	
	@Autowired
	PriseDao priseJpa;
	
	public Set<Prise> build(SeanceRequest seance){
		
		Set<Prise> prises  = new HashSet<>();
		
		
		for(Prise prise : seance.getPrises()) {	
			prises.add(priseJpa.save(prise));	
		}
		
		return prises;
	}
	
	

}
