package Pecadillo.isika.al.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import Pecadillo.isika.al.payload.request.SeanceRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Seance {

    @Id
    @GeneratedValue
    private Integer id;
    
    private Integer meteoId;
    
    private String Titre;
    
    private String description;
    
    @OneToMany(mappedBy="seance")
    private Set<Prise> prises;
    
	 @ManyToOne
	 @JoinColumn(name="user_id", nullable=false)
	 private  User user;

	 public Seance(SeanceRequest seance) {
		 this.description=seance.getDescription();
		 this.meteoId=seance.getMeteoId();
		 this.prises=seance.getPrises();
		 this.Titre= seance.getTitre();
		 
	 }
	 
}

