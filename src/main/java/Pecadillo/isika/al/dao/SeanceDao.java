package Pecadillo.isika.al.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Pecadillo.isika.al.model.Seance;
import Pecadillo.isika.al.model.User;



@Repository
public interface SeanceDao extends JpaRepository<Seance, Long> {
	
		Optional<List<Seance>> findAllByUser(User user);
	
}
