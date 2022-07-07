package Pecadillo.isika.al.controllers;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import Pecadillo.isika.al.dao.RoleRepository;
import Pecadillo.isika.al.dao.UserRepository;
import Pecadillo.isika.al.model.Role;
import Pecadillo.isika.al.model.User;
import Pecadillo.isika.al.payload.request.LoginRequest;
import Pecadillo.isika.al.payload.request.SignupRequest;
import Pecadillo.isika.al.payload.request.UserUpdateRequest;
import Pecadillo.isika.al.payload.response.JwtResponse;
import Pecadillo.isika.al.payload.response.MessageResponse;
import Pecadillo.isika.al.security.jwt.AuthTokenFilter;
import Pecadillo.isika.al.security.jwt.JwtUtils;
import Pecadillo.isika.al.security.services.UserDetailsImpl;
import Pecadillo.isika.al.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private static final Logger  LOGGER = Logger.getLogger(AuthController.class);
	
	@Value("${pecadille.app.siteURL}")
	private String SITE_URL;

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	AuthTokenFilter authTokenFilter;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		
		//Verification des credentials du login via authenticationManager
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		User user = userService.getUserbyUsername( userDetails.getUsername()).get();
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles,
												 user.getPrenom(),
												 user.getNom(),
												 user.getAdresse(),
												 user.getVille(),
												 user.getPays(),
												 user.getCodePostal()
												 ));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException  {
		
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Ce nom d'utilisateur est déja utilisé"));
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Cet Email est déja utilisé"));
		}
		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		
		String message = userService.userRegister(strRoles, roles, user, SITE_URL);
		
		
		return ResponseEntity.ok(new MessageResponse(message));
	}
	
    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }  
    
    @PostMapping("/updateuser")
	public ResponseEntity<?> updateUser(@RequestBody UserUpdateRequest userUpdateRequest,  HttpServletRequest request) {
    	
    	System.out.println("rentrer dans le update");
    	
    	LOGGER.info(userUpdateRequest.toString());
    	String username = authTokenFilter.getUserfromJwt(request);
    	
    	userService.saveAndFlushByUsername(username, userUpdateRequest);
    	
    	return ResponseEntity.ok().build();
    }


    
    
    @PostMapping("/verify")
    public Boolean verifyUser(@RequestBody Map<String,String> code) {
    	
    	System.out.println(code.get("code"));
        return userService.verify(code.get("code"));
    }
}
