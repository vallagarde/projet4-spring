package Pecadillo.isika.al.service;

import org.springframework.stereotype.Component;

import Pecadillo.isika.al.model.MeteoPreference;
import Pecadillo.isika.al.model.User;
import Pecadillo.isika.al.payload.request.MeteoPreferenceRequest;

@Component
public class MeteoPreferenceMapper {

	public MeteoPreference map(MeteoPreference meteoPreference ,MeteoPreferenceRequest meteoPrefRequest) {
		
		meteoPreference.setLatitude(meteoPrefRequest.getLatitude());
		meteoPreference.setLongitude(meteoPrefRequest.getLongitude());
		meteoPreference.setPluieMin(meteoPrefRequest.getPluieMin());
		meteoPreference.setPluieMax(meteoPrefRequest.getPluieMax());
		meteoPreference.setTempMin(meteoPrefRequest.getTempMin());
		meteoPreference.setTempMax(meteoPrefRequest.getTempMax());
		meteoPreference.setVentMin(meteoPrefRequest.getVentMin());
		meteoPreference.setVentMax(meteoPrefRequest.getVentMax());
		
		
		
		return meteoPreference;
	}
	
public MeteoPreference mapWithUser(MeteoPreference meteoPreference ,MeteoPreferenceRequest meteoPrefRequest, User user) {
		
		meteoPreference = map(meteoPreference, meteoPrefRequest);
		meteoPreference.setUser(user);
		
		
		
		return meteoPreference;
	}
	
}
