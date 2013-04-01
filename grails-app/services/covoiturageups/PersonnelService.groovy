package covoiturageups


import org.jcouchdb.db.Database
import org.jcouchdb.document.BaseDocument
import org.jcouchdb.document.ValueRow
import org.jcouchdb.exception.UpdateConflictException

@Grab(group='com.google.code.jcouchdb', module='jcouchdb', version='1.0.1-1')
class PersonnelService {
	def Database db
	
	private static final INSTANCE = new PersonnelService()
	static PersonnelService getInstance(){ return INSTANCE }
	private PersonnelService() {
		db = new Database("localhost", "covoiturageups");
	}
	
	def setDatabaseName(String s){
		db = new Database("localhost", s)
	}
	
	def flushDatabase(){
		for(Personnel p : getAllPersonnel()){
			deletePersonnel(p)
		}
	}
	
    boolean savePersonnel(Personnel p) throws UpdateConflictException {	
		// create the document in couchdb

		db.createDocument(generateBaseDocument(p));

		return true
    }
	
	//TODO fix, missing revision in basedoc
	def deletePersonnel(Personnel p){
		db.delete(generateBaseDocument(p))
	}
	
	//TODO notfound error?
	def getPersonnel(String email){
		BaseDocument doc = db.getDocument(BaseDocument.class, email)
		
		return generatePersonnel(doc)
	}
	
	List<Personnel> getAllPersonnel(){
		List<Personnel> result = new ArrayList<Personnel>()
				
		for (ValueRow<Object> row : db.listDocuments(null, null).getRows()) {
			result.add(this.getPersonnel(row.getId()))
		}
		
		return result
	}
	
	def generatePersonnel(BaseDocument doc){
		return new Personnel(	nom:doc.getProperty("nom"),
								prenom:doc.getProperty("prenom"),
								email:doc.getProperty("email"),
								adresse:doc.getProperty("adresse"),
								longitude:doc.getProperty("longitude"),
								latitude:doc.getProperty("latitude"),
								revision:doc.getRevision())
	}
	
	def generateBaseDocument(Personnel p){
		BaseDocument doc = new BaseDocument();
		doc.setId(p.getEmail());
		
		doc.setProperty("nom", p.getNom());
		doc.setProperty("prenom", p.getPrenom());
		doc.setProperty("email", p.getEmail());
		doc.setProperty("adresse", p.getAdresse());
		doc.setProperty("latitude", p.getLatitude());
		doc.setProperty("longitude", p.getLongitude());
		
		if(p.getRevision() != null){
			doc.setRevision(p.getRevision())
		}
		
		return doc
	}
}

