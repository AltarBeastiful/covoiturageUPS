package covoiturageups

class SubscriptionEndpoint {
	def static namespace = "http://www.covoiturageups.com/v1/definitions"
	
	def invoke = { request, response ->
		
		// Using the incoming document
		println "Subscribtion Request Received!"
		println "Prenom: ${request.Subscription.Prenom[0].text()}"
		println "Nom: ${request.Subscription.Nom[0].text()}"
		println "Adresse: ${request.Subscription.Adresse[0].text()}"
		println "Mail: ${request.Subscription.Mail[0].text()}"
	
		// Typically you'd invoke some internal business services here
		//TODO : register the personn
		//TODO: calculate his coordinates with open street map
		// Preparing the response document
		response.SubscribtionResponse(xmlns: namespace) {
		  status('complete')
		}
	}
}