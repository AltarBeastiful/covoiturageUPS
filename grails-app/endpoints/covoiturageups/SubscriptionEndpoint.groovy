package covoiturageups

class SubscriptionEndpoint {
	def static namespace = "http://www.covoiturageups.com/v1/definitions"
	
	def invoke = { request, response ->
		
		// Using the incoming document
		println "Subscribtion Request Received!"
		println "Prenom: ${request.Subscribtion.Prenom}"
		println "Nom: ${request.Subscribtion.Nom}"
		println "Mail: ${request.Subscribtion.Mail}"
		println "Adresse: ${request.Subscribtion.Adresse}"
	
		// Typically you'd invoke some internal business services here
	
		// Preparing the response document
		response.SubscribtionResponse(xmlns: namespace) {
		  status('complete')
		}
	}
	

}