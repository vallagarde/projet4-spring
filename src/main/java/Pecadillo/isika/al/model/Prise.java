package Pecadillo.isika.al.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	 @JsonBackReference
	 private Seance seance;

}
