package Pecadillo.isika.al.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Pecadillo.isika.al.model.MeteoPreference;
import Pecadillo.isika.al.model.User;

@Repository
public interface MeteoPreferenceDao extends JpaRepository<MeteoPreference, Long> {
	
	List<MeteoPreference> findAllByUser(User user);

}
