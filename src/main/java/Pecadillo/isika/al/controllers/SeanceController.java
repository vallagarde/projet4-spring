package Pecadillo.isika.al.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Pecadillo.isika.al.dao.SeanceDao;
import Pecadillo.isika.al.exception.UserNotFindException;
import Pecadillo.isika.al.model.Seance;
import Pecadillo.isika.al.model.SeanceBuilder;
import Pecadillo.isika.al.payload.request.SeanceRequest;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/seance")
public class SeanceController {
	
	@Autowired
	private SeanceDao seanceJpa;
	
	@Autowired
	private SeanceBuilder seanceBuilder;
	
	@PostMapping("/seances")
    public ResponseEntity<Void> createSeance(@Valid @RequestBody SeanceRequest seanceRequest){

		System.out.println(seanceRequest.toString());
		
		Seance seance= new Seance();
		
		try {
			seance = seanceBuilder.SeanceBuild(seanceRequest);
		} catch (UserNotFindException e) {
			e.printStackTrace();
		}

        Seance savedSeance = seanceJpa.save(seance);

        System.out.println(savedSeance);

        return ResponseEntity.ok().build();
        
    }
	
	@GetMapping(path = "/seances")
	public List<Seance> getAllSeances(){
		return seanceJpa.findAll();
	}

}
