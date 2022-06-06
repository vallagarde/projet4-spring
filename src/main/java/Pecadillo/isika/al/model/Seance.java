package Pecadillo.isika.al.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


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

}

