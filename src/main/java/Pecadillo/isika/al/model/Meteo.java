package Pecadillo.isika.al.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Meteo {

    @Id
    @GeneratedValue
    private Integer id;

    private String label;
    private String description;

}

