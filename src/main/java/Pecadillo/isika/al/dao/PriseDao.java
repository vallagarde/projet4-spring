package Pecadillo.isika.al.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Pecadillo.isika.al.model.Prise;


@Repository
public interface PriseDao extends JpaRepository<Prise, Long>  {

}
