package Pecadillo.isika.al.service;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import Pecadillo.isika.al.dao.SeanceDao;
import Pecadillo.isika.al.model.Seance;

@CrossOrigin(origins = "*")
@RestController
public class MeteoService {
	
	private SeanceDao meteoJpa;
	
	public List<Seance> getAllSeances(){
		return meteoJpa.findAll();
	}

}
