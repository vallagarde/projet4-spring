package Pecadillo.isika.al.service;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import Pecadillo.isika.al.dao.RoleRepository;
import Pecadillo.isika.al.dao.UserRepository;
import Pecadillo.isika.al.model.ERole;
import Pecadillo.isika.al.model.Role;
import Pecadillo.isika.al.model.User;
import net.bytebuddy.utility.RandomString;

@Component
public class UserService {
	
	@Autowired 
	RoleRepository roleRepository;
	
	@Autowired 
	UserRepository userRepository;
	
	@Autowired
    JavaMailSender mailSender;
	
	
	public String userRegister(Set<String> strRoles, Set<Role> roles, User user, String siteURL) throws UnsupportedEncodingException, MessagingException {
		
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);
					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		String randomCode = RandomString.make(64);
	    user.setVerificationCode(randomCode);
		sendVerificationEmail(user, siteURL);

		userRepository.save(user);
		
		
		return "Vous vous êtes bien enregistrés";
		
		
	}
	
	private void sendVerificationEmail(User user, String siteURL)
	        throws MessagingException, UnsupportedEncodingException {
	    String toAddress = user.getEmail();
	    String fromAddress = "pecadille4@gmail.com";
	    String senderName = "Pecadille";
	    String subject = "Veuillez valider votre inscription";
	    String content = "Cher [[name]],<br>"
	            + "Cliquez sur le lien ci-dessous pour alider votre inscription:<br>"
	            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFICATION</a></h3>"
	            + "Merci,<br>"
	            + "L'équipe Pecadille";
	     
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);
	     
	    content = content.replace("[[name]]", user.getUsername());
	    String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
	     
	    content = content.replace("[[URL]]", verifyURL);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	     
	}
	
	public boolean verify(String verificationCode) {
	    Optional<User> userO = userRepository.findByVerificationCode(verificationCode);
	     
	    if (userO.isEmpty() || userO.get().isEnabled()) {
	    	System.out.println("getbynull");
	        return false;
	    } else {
	    	System.out.println("changUser");
	    	User user = userO.get();
	        user.setVerificationCode(null);
	        user.setEnabled(true);
	        userRepository.save(user);
	         
	        return true;
	    }
	     
	}
	
	

}
