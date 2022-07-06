package Pecadillo.isika.al;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import Pecadillo.isika.al.dao.RoleRepository;
import Pecadillo.isika.al.model.ERole;
import Pecadillo.isika.al.model.Role;

@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		
		
		
		
		SpringApplication.run(Application.class, args);
		
		
	}
	@Bean
    CommandLineRunner init(RoleRepository roleRepository) {

        return args -> {

            Optional<Role> adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
            if (!adminRole.isPresent()) {
                Role newAdminRole = new Role(ERole.ROLE_ADMIN);
                roleRepository.save(newAdminRole);
            }
            
            Optional<Role> artistRole = roleRepository.findByName(ERole.ROLE_MODERATOR);
            if (!artistRole.isPresent()) {
                Role newAdminRole = new Role(ERole.ROLE_MODERATOR);
                roleRepository.save(newAdminRole);
            }
            
            

            Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_USER);
            if (!userRole.isPresent()) {
                Role newUserRole = new Role(ERole.ROLE_USER);
                roleRepository.save(newUserRole);
            }
        };
	}
	

}
