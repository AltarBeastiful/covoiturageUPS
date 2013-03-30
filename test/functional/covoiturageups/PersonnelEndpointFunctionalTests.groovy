package covoiturageups



import org.codehaus.groovy.grails.plugins.spring.ws.EndpointFunctionalTestCase

class PersonnelEndpointFunctionalTests extends EndpointFunctionalTestCase {

	def serviceURL = "http://localhost:8080/covoiturageUPS/services/v1/Subscribtion"

	def namespace = "http://www.covoiturageups.com/v1/definitions"

	void setUp(){
		super.setUp()
		webServiceTemplate.setDefaultUri(serviceURL)
	}

	void testSOAPDocumentService() {
		def response = withEndpointRequest(serviceURL) {
			SubscriptionRequest(xmlns: namespace) {
				Subscription{
					Prenom("Toto")
					Nom("Auzoo")
					Mail("totoauzoo@univ-tlse3.fr")
					Adresse("11 rue bernard mule")
				}
				
			}
		}

		def status = response.status
		assert status == "complete"
	}
}