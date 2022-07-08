package Pecadillo.isika.al.service;

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

}
