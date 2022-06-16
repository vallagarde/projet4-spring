package Pecadillo.isika.al.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Pecadillo.isika.al.model.Seance;



@Repository
public interface SeanceDao extends JpaRepository<Seance, Long> {
}
