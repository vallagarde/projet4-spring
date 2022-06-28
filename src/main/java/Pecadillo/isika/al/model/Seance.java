package Pecadillo.isika.al.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import Pecadillo.isika.al.payload.request.SeanceRequest;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Seance {

    @Id
    @GeneratedValue
    private Integer id;
    
    private Integer meteoId;
    
    private String Titre;
    
    private String description;
    
    private Long latitude;
    
    private Long longitude;
    
    @OneToMany(mappedBy="seance")
    @JsonManagedReference
    private Set<Prise> prises;
    
	 @ManyToOne
	 @JoinColumn(name="user_id", nullable=false)
	 private  User user;


	 
}

