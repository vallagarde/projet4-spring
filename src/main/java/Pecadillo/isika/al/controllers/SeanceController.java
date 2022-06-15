package Pecadillo.isika.al.controllers;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Pecadillo.isika.al.dao.SeanceDao;
import Pecadillo.isika.al.model.Seance;
import Pecadillo.isika.al.payload.request.SeanceRequest;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/seance")
public class SeanceController {
	
	private SeanceDao seanceJpa;
	
	@RolesAllowed("ROLE_USER")
	@PostMapping("/seances")
    public ResponseEntity<Void> createSeance(@RequestBody Seance seance){

		
        Seance savedSeance = seanceJpa.save(seance);

        System.out.println(savedSeance);

        return ResponseEntity.noContent().build();
    }
	
	@RolesAllowed("ROLE_USER")
	@GetMapping(path = "/seances")
	public List<Seance> getAllSeances(){
		return seanceJpa.findAll();
	}

}
