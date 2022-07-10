package Pecadillo.isika.al.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class NodeRest {
	
	
	@Value("${nodeREST.app.siteURL}")
	private String NODE_URL;

	WebClient client = WebClient.create(NODE_URL);
	
	
	public Boolean askMeteo(String date, double latitude, double longitude, double tempMin, double tempMax, double ventMin, double ventMax ) {
		WebClient.ResponseSpec responseSpec = client.get()
			    .uri(NODE_URL+"/api/meteo/one/"+date+"/"+latitude+"/"+longitude+"/"+tempMin+"/"+tempMax+"/"+ventMin+"/"+ventMax)
			    .retrieve();
		
		String responseString = responseSpec.bodyToMono(String.class).block();
		
		if (responseString.equals("true"))
			return true;
		
		return false;
	}
	
}
