package covoiturageups

import external.OpenStreetMapCaller
import org.jcouchdb.exception.UpdateConflictException

@Grab(group='com.google.code.jcouchdb', module='jcouchdb', version='1.0.1-1')
class SubscriptionEndpoint {
	def static namespace = "http://www.covoiturageups.com/v1/definitions"
	
	def invoke = { request, response ->
		
		// Using the incoming document
		println "Subscribtion Request Received!"
		println "Prenom: ${request.Subscription.Prenom[0].text()}"
		println "Nom: ${request.Subscription.Nom[0].text()}"
		println "Adresse: " + request.Subscription.Adresse[0].text()
		println "Mail: ${request.Subscription.Mail[0].text()}"
	
		//calculate his coordinates with open street map
		def gps =  OpenStreetMapCaller.getCoordinatesFromAddress(request.Subscription.Adresse[0].text())
		
		if(gps.size() == 0){
			//TODO Erreur adresse non trouvé
		}
		
		//TODO register the person
		def controller = new PersonnelController()
		
		controller.params.putAll([nom: request.Subscription.Nom[0].text(), prenom: request.Subscription.Prenom[0].text(),
								  adresse: request.Subscription.Adresse[0].text(), email: request.Subscription.Mail[0].text(),
								  latitude: gps[0], longitude: gps[1]] )
		println "Trying to save"
		try{
			if(!controller.save()){
				//TODO erreur params non valide
				println "Parameters not valid"
			}
		}catch(UpdateConflictException e){
			//TODO erreur email deja utilise
			println "Email already in use"
		}
		
		// Preparing the response document
		//TODO inscription réussi
		println "Success!"
		response.SubscribtionResponse(xmlns: namespace) {
		  status('complete')
		}
	}
}