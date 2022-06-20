package Pecadillo.isika.al.payload.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import Pecadillo.isika.al.model.Prise;

public class SeanceRequest {
	
    private Integer meteoId;
    
    private Integer meteoIndex;
    
	@NotBlank
    private String titre;
    
	@NotBlank
    private String description;
	
	
	private String userEmail;
    
    private Set<Prise> prises;
    
    

	public Integer getMeteoId() {
		return meteoId;
	}

	public void setMeteoId(Integer meteoId) {
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
	

	public Integer getMeteoIndex() {
		return meteoIndex;
	}

	public void setMeteoIndex(Integer meteoIndex) {
		this.meteoIndex = meteoIndex;
	}

	@Override
	public String toString() {
		return "SeanceRequest [meteoId=" + meteoId + ", meteoIndex=" + meteoIndex + ", titre=" + titre
				+ ", description=" + description + ", userEmail=" + userEmail + ", prises=" + prises + "]";
	}




    

}
