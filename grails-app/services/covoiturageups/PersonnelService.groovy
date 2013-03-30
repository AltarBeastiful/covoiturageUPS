package covoiturageups

//@Grab(group='com.google.code.svenson', module='svenson', version='1.4.0')
import groovy.lang.Grab;
import org.jcouchdb.db.Database

@Grab(group='com.google.code.jcouchdb', module='jcouchdb', version='1.0.1-1')
class PersonnelService {
	def Database db
	
	private static final INSTANCE = new PersonnelService()
	static PersonnelService getInstance(){ return INSTANCE }
	private PersonnelService() {
		db = new Database("localhost", "covoiturageups");
	}
	
    def savePersonnel(Personnel p) {
		Map<String,String> doc = new HashMap<String, String>();
		doc.put("nom", p.getNom());
		doc.put("prenom", p.getPrenom());
		doc.put("email", p.getEmail());
		doc.put("adresse", p.getAdresse());
		doc.put("latitude", p.getLatitude());
		doc.put("longitude", p.getLongitude());
	
		// create the document in couchdb
		db.createDocument(doc);
    }
}
