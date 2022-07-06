package Pecadillo.isika.al.payload.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import Pecadillo.isika.al.model.Prise;

public class SeanceRequest {
	
    private String meteoId;
    
    private Long meteoIndex;
    
	@NotBlank
    private String titre;
    
	@NotBlank
    private String description;
	
	
	private String userEmail;
    
    private Set<Prise> prises;
    
    private double latitude;
    
    private double longitude;
    
    private String date;
    
    

	public String getMeteoId() {
		return meteoId;
	}

	public void setMeteoId(String meteoId) {
		this.meteoId = meteoId;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Prise> getPrises() {
		return prises;
	}

	public void setPrises(Set<Prise> prises) {
		this.prises = prises;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	

	public Long getMeteoIndex() {
		return meteoIndex;
	}

	public void setMeteoIndex(Long meteoIndex) {
		this.meteoIndex = meteoIndex;
	}


	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "SeanceRequest [meteoId=" + meteoId + ", meteoIndex=" + meteoIndex + ", titre=" + titre
				+ ", description=" + description + ", userEmail=" + userEmail + ", prises=" + prises + ", latitude="
				+ latitude + ", longitude=" + longitude + ", date=" + date + "]";
	}





    

}
