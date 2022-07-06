package Pecadillo.isika.al.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Pecadillo.isika.al.dao.PriseDao;
import Pecadillo.isika.al.dao.SeanceDao;
import Pecadillo.isika.al.exception.UserNotFindException;
import Pecadillo.isika.al.model.Prise;
import Pecadillo.isika.al.model.Seance;
import Pecadillo.isika.al.model.SeanceBuilder;
import Pecadillo.isika.al.payload.request.SeanceRequest;
import Pecadillo.isika.al.security.jwt.AuthTokenFilter;
import Pecadillo.isika.al.service.SeanceService;
import Pecadillo.isika.al.service.UserService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/seance")
public class SeanceController {
	
	
	private static final Logger  LOGGER = Logger.getLogger(SeanceController.class);
	
	@Autowired
	private SeanceDao seanceJpa;
	
	@Autowired 
	PriseDao priseJpa;
	
	
	@Autowired
	UserService userService;
	
	@Autowired
	private SeanceBuilder seanceBuilder;
	
	@Autowired 
	SeanceService seanceService;
	
	@Autowired
	AuthTokenFilter authTokenFilter;
	
	
	@PostMapping("/seances")
    public ResponseEntity<Void> createSeance(@Valid @RequestBody SeanceRequest seanceRequest){

		LOGGER.info(seanceRequest.toString());
		
		Seance seance= new Seance();
		
		try {
			seance = seanceBuilder.SeanceBuild(seanceRequest);
		} catch (UserNotFindException e) {
			LOGGER.error(e);
		}

		
		
		
        Seance savedSeance = seanceJpa.save(seance);
        
        for (Prise prise : seanceRequest.getPrises()) {
        	prise.setSeance(savedSeance);
        	priseJpa.save(prise);
        }

        System.out.println(savedSeance);

        return ResponseEntity.ok().build();
        
    }
	
	@GetMapping(path = "/seances")
	public List<Seance> getAllSeances(HttpServletRequest request){
		
		String username = authTokenFilter.getUserfromJwt(request);
		
		return seanceService.findSeancesByUserId(username).get();
	}
	


}
