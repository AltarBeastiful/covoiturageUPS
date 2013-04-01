package covoiturageups

import external.OpenStreetMapCaller

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
		
		// Preparing the response document
		response.SubscribtionResponse(xmlns: namespace) {
		  status('complete')
		}
	}
}