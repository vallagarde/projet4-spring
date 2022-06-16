package Pecadillo.isika.al.exception;

public class UserNotFindException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5331383294174943066L;
	
	String message = "Le User n'a pas été trouvé avec cet id";
	
	public UserNotFindException(){
		
		
	}

}
