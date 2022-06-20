package Pecadillo.isika.al.dao;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Pecadillo.isika.al.model.Prise;
import Pecadillo.isika.al.model.Seance;

@Repository
public interface PriseDao extends JpaRepository<Prise, Long>  {

}
