package Pecadillo.isika.al.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import Pecadillo.isika.al.dao.SeanceDao;
import Pecadillo.isika.al.model.Seance;

@CrossOrigin(origins = "*")
@RestController
public class MeteoService {
	
	private SeanceDao seanceJpa;
	
    @PostMapping("/seances")
    public ResponseEntity<Void> createSeance(@RequestBody Seance seance){

        Seance newSeance=seanceJpa.save(seance);

        System.out.println(newSeance);

        return ResponseEntity.noContent().build();
    }
	
	@GetMapping(path = "/seances")
	public List<Seance> getAllSeances(){
		return seanceJpa.findAll();
	}
	
    @PutMapping("/seances/{id}")
    public ResponseEntity<Seance> updateSeance(@PathVariable Integer id, @RequestBody Seance seance){

        seance.setId(id);

        Seance newSeance=seanceJpa.save(seance);

        System.out.println(newSeance);

        return new ResponseEntity<Seance>(newSeance, HttpStatus.OK);
    }

}
