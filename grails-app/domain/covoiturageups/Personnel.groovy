package covoiturageups

class Personnel {
	String nom
	String prenom
	String email
	String adresse
	Double latitude
	Double longitude
	
	String toString() {
		return prenom + " " + nom + " " + email + " " + adresse 
	}

    static constraints = {
		prenom blank: false
		nom blank: false
		email email: true, blank: false, matches: /.*\@univ-tlse3.fr$/
		adresse blank:false
		latitude blank: false
		longitude blank: false
    }
}
