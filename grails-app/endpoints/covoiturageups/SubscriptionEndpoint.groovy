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

		// Calculate coordinates with open street map
		def gps =  OpenStreetMapCaller.getCoordinatesFromAddress(request.Subscription.Adresse[0].text())
		if (!gps) {
			println "Adress unfound with Open Street Map Caller"
			response.SubscriptionResponse(xmlns: namespace) {
				result('KO')
				status('200')
				message('Adresse postale non connue de Open Street Map')
			}
		}
		else { 
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
			response.SubscriptionResponse(xmlns: namespace) {
				result('OK')
				message('Subscription done')
			}
			println "Insertion into database done"
		}
	}
}