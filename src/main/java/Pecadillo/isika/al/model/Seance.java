package Pecadillo.isika.al.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


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
    private Long id;
    
    private String meteoId;
    
    private Long meteoIndex;
    
    private String Titre;
    
    private String description;
    
    private double latitude;
    
    private double longitude;
    
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @OneToMany(mappedBy="seance")
    @JsonManagedReference
    private Set<Prise> prises;
    
	 @ManyToOne
	 @JoinColumn(name="user_id", nullable=false)
	 @JsonBackReference
	 private  User user;


	 
}

