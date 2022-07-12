package Pecadillo.isika.al.service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Pecadillo.isika.al.dao.MeteoPreferenceDao;
import Pecadillo.isika.al.dao.UserRepository;
import Pecadillo.isika.al.model.MeteoPreference;
import Pecadillo.isika.al.model.User;
import Pecadillo.isika.al.payload.request.MeteoPreferenceRequest;

@Component
public class MeteoPreferenceService {
	
	@Autowired
	MeteoPreferenceDao meteoDao;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MeteoPreferenceMapper meteoPreferenceMapper;
	
	@Autowired
	NodeRest nodeRest;
	
	public List<MeteoPreference> findAllByUser(User user){	
		return meteoDao.findAll();
	}
	
	public List<MeteoPreference> findAllByUsername(String username){
		User user = userRepository.findByUsername(username).get();
		return findAllByUser(user); 
		
	}
	
	public Optional<MeteoPreference> findById(Long id) {
		return meteoDao.findById(id);
	}
	
	public void deleteById(Long id) {
		meteoDao.deleteById(id);
	}
	
	public void delete(MeteoPreferenceRequest meteoPreferenceRequest) {
		deleteById(meteoPreferenceRequest.getId());
	}
	
	public MeteoPreference createMeteoPreference(MeteoPreference meteoPref){
		return meteoDao.save(meteoPref);
	}
	
	public MeteoPreference createMeteoPreferenceWithUser(MeteoPreferenceRequest meteoPrefRequest, String username) {
		MeteoPreference meteoPref = new MeteoPreference();
		User user = userRepository.findByUsername(username).get();
		
		return createMeteoPreference(meteoPreferenceMapper.mapWithUser(meteoPref, meteoPrefRequest, user));
		
	}
	
	public MeteoPreference updateMeteoPreference(MeteoPreferenceRequest meteoPrefRequest) {
		
		Optional<MeteoPreference> meteoPreference = findById(meteoPrefRequest.getId());
		
		return  updateMeteoPreference(meteoPreferenceMapper.map(meteoPreference.get(), meteoPrefRequest));
		
	}
	
	public MeteoPreference updateMeteoPreference(MeteoPreference meteoPref) {
		
		return meteoDao.saveAndFlush(meteoPref);
	}
	
	//Fonction appelée a la connection d'un utilisateur qui vérifie si les météos d'aujourd'hui et demain rentrent dans ses considérations
	
	public void getMeteosValidation(String username) {
		
		List<MeteoPreference> meteosPref = findAllByUsername(username);
		Instant i = Instant.now();
		String now = i.atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE);
		Instant ii = i.plus(1, ChronoUnit.DAYS);
        String tmw = ii.atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE);
        
        for (MeteoPreference meteoPref : meteosPref) {
        	
        	meteoPref.setAJD(nodeRest.askMeteo(now, meteoPref.getLatitude(), meteoPref.getLongitude(), meteoPref.getTempMin(), meteoPref.getTempMax(), (meteoPref.getVentMin()), (meteoPref.getVentMax())));
        	meteoPref.setDMN(nodeRest.askMeteo(tmw, meteoPref.getLatitude(), meteoPref.getLongitude(), meteoPref.getTempMin(), meteoPref.getTempMax(),  (meteoPref.getVentMin()), (meteoPref.getVentMax())));

        	meteoDao.saveAndFlush(meteoPref);
        }
        
        
		
	}

}
