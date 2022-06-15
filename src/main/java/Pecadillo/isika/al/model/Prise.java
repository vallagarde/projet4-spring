package Pecadillo.isika.al.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Prise {
	
	 @Id
	 @GeneratedValue
	 private Integer id;
	 
	 private String espece;
	 private Integer taille;
	 private Integer poids;
	 
	 @ManyToOne
	 @JoinColumn(name="seance_id", nullable=false)
	 private Seance seance;

}
