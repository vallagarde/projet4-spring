package Pecadillo.isika.al.payload.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import Pecadillo.isika.al.model.Prise;

public class SeanceRequest {
	
	@NotBlank
    private Integer meteoId;
    
	@NotBlank
    private String Titre;
    
	@NotBlank
    private String description;
    
    private Set<Prise> prises;

	public Integer getMeteoId() {
		return meteoId;
	}

	public void setMeteoId(Integer meteoId) {
		this.meteoId = meteoId;
	}

	public String getTitre() {
		return Titre;
	}

	public void setTitre(String titre) {
		Titre = titre;
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
    

}
