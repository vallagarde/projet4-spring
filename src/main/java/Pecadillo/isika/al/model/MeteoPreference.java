package Pecadillo.isika.al.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MeteoPreference {
	
    @Id
    @GeneratedValue
    private Long id;
    
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	@JsonBackReference
	private  User user;
	
	private double tempMin;
	
	private double tempMax;
	
	private double ventMin;
	
	private double ventMax;
	
	private double pluieMin;
	
	private double pluieMax;
	
	private double latitude;
	
	private double longitude;
	
	private Boolean AJD;
	
	private Boolean DMN;
	
	

}
