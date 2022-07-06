package Pecadillo.isika.al.payload.request;




public class UserUpdateRequest {
	
	
    
    private String prenom;
    private String nom;
    private String adresse;
    private String ville;
    private String pays;
    private Integer codePostal;
    

	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public Integer getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}
	@Override
	public String toString() {
		return "UserUpdateRequest [prenom=" + prenom + ", nom=" + nom + ", adresse="
				+ adresse + ", ville=" + ville + ", pays=" + pays + ", codePostal=" + codePostal + "]";
	}

    
    

}
