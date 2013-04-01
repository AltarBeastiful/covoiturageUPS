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
		// OK Test
		def response = withEndpointRequest(serviceURL) {
			SubscriptionRequest(xmlns: namespace) {
				Subscription{
					Prenom("Toto")
					Nom("Auzoo")
					Mail("totoauzoo@univ-tlse3.fr")
					Adresse("11 rue l'Homme armee, Toulouse")
				}
			}
		}
		
		/*
		// KO : test failed 
			// code 100 : mail already used
		def response = withEndpointRequest(serviceURL) {
			SubscriptionRequest(xmlns: namespace) {
				Subscription{
					Prenom("Toto")
					Nom("Auzoo")
					Mail("totoauzoo@univ-tlse3.fr")
					Adresse("11 rue l'Homme armee, Toulouse")
				}
			}
		}
			// code 110 : Invalid Mail
		def response = withEndpointRequest(serviceURL) {
			SubscriptionRequest(xmlns: namespace) {
				Subscription{
					Prenom("Toto")
					Nom("Auzoo")
					Mail("fail-univ-tlse3.fr")
					Adresse("11 rue l'Homme armee, Toulouse")
				}
			}
		}
			// code 200 : Adresse unknown by OSM
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
		*/
		
		def status = response.status
		assert status == "complete"
	}
}