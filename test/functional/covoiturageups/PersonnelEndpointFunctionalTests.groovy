package covoiturageups

import org.codehaus.groovy.grails.plugins.spring.ws.EndpointFunctionalTestCase
import org.junit.Before;
import org.junit.Test;

class PersonnelEndpointFunctionalTests extends EndpointFunctionalTestCase {

	def serviceURL = "http://localhost:8080/covoiturageUPS/services/v1/Subscribtion"
	def namespace = "http://www.covoiturageups.com/v1/definitions"

	void setUp(){
		super.setUp()
		webServiceTemplate.setDefaultUri(serviceURL)
		
	}
	
	// Execute test in the order we want
	void testPersonnelEndpoint(){
		setupDB()
		subscriptionError200()
		subscriptionError110()
		subscriptionOK()
		subscriptionError100()
	}
	
	void setupDB() {
		PersonnelService.getInstance().setDatabaseName("covoiturageupstest")
		PersonnelService.getInstance().flushDatabase()
	}
	
	// code erreur 200 : Adresse postale non connue de Open Street Map
	void subscriptionError200() {
		def response = withEndpointRequest(serviceURL) {
			SubscriptionRequest(xmlns: namespace) {
				Subscription{
					Prenom("TotoPasBien")
					Nom("Auzoo2")
					Mail("totoauzoo@univ-tlse3.fr")
					Adresse("rue du paradis Toulouse") // unworking : error 200
				}
			}
		}
		def result = response.result
		def status = response.status
		assert result == "KO"
		assert status == "200"
	}
	
	// code erreur 110 : Adresse email invalide
	void subscriptionError110() {
		def response = withEndpointRequest(serviceURL) {
			SubscriptionRequest(xmlns: namespace) {
				Subscription{
					Prenom("TotoPasBien")
					Nom("Auzoo2")
					Mail("failarobauniv-tlse3.fr")
					Adresse("Rue Bayard Toulouse")
				}
			}
		}
		def result = response.result
		def status = response.status
		assert result == "KO"
		assert status == "110"
	}

	void subscriptionOK() {
		// OK Test
		def response = withEndpointRequest(serviceURL) {
			SubscriptionRequest(xmlns: namespace) {
				Subscription{
					Prenom("TotoPasBien")
					Nom("Auzoo2")
					Mail("totoauzoo@univ-tlse3.fr")
					Adresse("Rue Bayard Toulouse") // working adress
				}
			}
		}
				
		def result = response.result
		def status = response.status
		assert result == "OK"
	}
	
	//code erreur 100 : Adresse email d�j� utilis�e
	void subscriptionError100() {
		// my instance
		def response = withEndpointRequest(serviceURL) {
			SubscriptionRequest(xmlns: namespace) {
				Subscription{
					Prenom("TotoPasBien")
					Nom("Auzoo2")
					Mail("totoauzoo@univ-tlse3.fr")
					Adresse("Rue Bayard Toulouse") // working adress
				}
			}
		}
		def result = response.result
		def status = response.status
		assert result == "KO"
		assert status == "100"
		
//		response = withEndpointRequest(serviceURL) {
//			SubscriptionRequest(xmlns: namespace) {
//				Subscription{
//					Prenom("Franck")
//					Nom("Arrecot")
//					Mail("franck@univ-tlse3.fr")
//					Adresse("place du capitole toulouse") // working adress
//				}
//			}
//		}
//		
//		response = withEndpointRequest(serviceURL) {
//			SubscriptionRequest(xmlns: namespace) {
//				Subscription{
//					Prenom("Remi")
//					Nom("Benoit")
//					Mail("remi@univ-tlse3.fr")
//					Adresse("Rue bernard mule Toulouse") // working adress
//				}
//			}
//		}
//		
//		response = withEndpointRequest(serviceURL) {
//			SubscriptionRequest(xmlns: namespace) {
//				Subscription{
//					Prenom("Paolo")
//					Nom("Devathaire")
//					Mail("paolo@univ-tlse3.fr")
//					Adresse("rue de la victoire tarbes") // working adress
//				}
//			}
//		}
	}

}