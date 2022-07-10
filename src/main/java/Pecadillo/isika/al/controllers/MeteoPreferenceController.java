package Pecadillo.isika.al.controllers;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Pecadillo.isika.al.model.MeteoPreference;
import Pecadillo.isika.al.payload.request.MeteoPreferenceRequest;
import Pecadillo.isika.al.security.jwt.AuthTokenFilter;
import Pecadillo.isika.al.service.MeteoPreferenceService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/meteo")
public class MeteoPreferenceController {
	
	@Autowired
	MeteoPreferenceService meteoPreferenceService;
	
	@Autowired
	AuthTokenFilter authTokenFilter;
	
	private static final Logger  LOGGER = Logger.getLogger(MeteoPreferenceController.class);
	
	@PostMapping("/preference")
    public ResponseEntity<Void> createPreference(@Valid @RequestBody MeteoPreferenceRequest meteoRequest, HttpServletRequest request) throws ParseException{

		LOGGER.info(meteoRequest.toString());
		String username = authTokenFilter.getUserfromJwt(request);

		
		meteoPreferenceService.createMeteoPreferenceWithUser(meteoRequest, username);

        return ResponseEntity.ok().build();
        
    }
	
	@GetMapping(path = "/preferences")
	public List<MeteoPreference> getAllPreferences(HttpServletRequest request){
		
		String username = authTokenFilter.getUserfromJwt(request);
		
		return meteoPreferenceService.findAllByUsername(username);
	}
	
	@PutMapping(path = "/preference")
	public ResponseEntity<Void> updatePreference(@Valid @RequestBody MeteoPreferenceRequest meteoRequest){
		
		meteoPreferenceService.updateMeteoPreference(meteoRequest);
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(path = "/preference/{id}")
	public ResponseEntity<Void> deletePreference(@PathVariable("id") Long id){
		
		meteoPreferenceService.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
	
	
	

}
