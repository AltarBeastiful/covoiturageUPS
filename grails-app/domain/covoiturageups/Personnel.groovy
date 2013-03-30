package covoiturageups

class Personnel {
	String nom
	String prenom
	String email
	String adresse
	
	String toString() {
		return prenom + " " + nom + " " + email + " " + adresse 
	}

    static constraints = {
		prenom blank: false
		nom blank: false
		email email: true, blank: false
		adresse blank:false
    }
}
