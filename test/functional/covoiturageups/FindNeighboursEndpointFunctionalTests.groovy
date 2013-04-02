package covoiturageups

import org.codehaus.groovy.grails.plugins.spring.ws.EndpointFunctionalTestCase
import org.springframework.web.context.request.RequestContextHolder
import org.junit.Before;
import org.junit.Test;

class FindNeighboursEndpointFunctionalTests extends EndpointFunctionalTestCase {
	
	def serviceURL = "http://localhost:8080/covoiturageUPS/services/v1/FindNeighbours"
	def namespace = "http://www.covoiturageups.com/v1/definitions"
	def controller

    void setUp(){
      super.setUp()
      webServiceTemplate.setDefaultUri(serviceURL)
	  
    }
	
	// Execute test in the order we want
	void testPersonnelEndpoint(){
		setupDB()
		
		userNotExists()
		populateCaller()
		noNeighbours()
		populateNeighbours()
		allNeighbours()
		someNeighbours()
		noNeighboursInRadius()
	}
	
	void setupDB() {
		controller = new PersonnelController()
		
		println "Calling setup"
		PersonnelService.getInstance().setDatabaseName("covoiturageupstest")
		PersonnelService.getInstance().flushDatabase()
	}
	
	void populateCaller(){
		Map myParams = [nom: 'Auzoo2',
						prenom: 'TotoPasBien',
						email: 'totoauzoo@univ-tlse3.fr',
						adresse: 'rue Bayard Toulouse',
						latitude: 43.5936951,
						longitude: 1.4380074]
		
		assertTrue PersonnelService.getInstance().savePersonnel(new Personnel(myParams))
	}
	
	void populateNeighbours() {
		Map myParams = [nom: 'Arrecot',
						prenom: 'franc',
						email: 'franck@univ-tlse3.fr',
						adresse: 'place du capitole toulouse',
						latitude: 43.6043956,
						longitude: 1.44335225594076]
		
		assertTrue PersonnelService.getInstance().savePersonnel(new Personnel(myParams))
		
		myParams = [nom: 'Devathaire',
					prenom: 'Paolo',
					email: 'paolo@univ-tlse3.fr',
					adresse: 'rue de la victoire tarbes',
					latitude: 43.2343555,
					longitude: 0.0706346]
		
		assertTrue PersonnelService.getInstance().savePersonnel(new Personnel(myParams))
		
		myParams = [nom: 'Benoit',
					prenom: 'Remi',
					email: 'remi@univ-tlse3.fr',
					adresse: 'Rue bernard mule Toulouse',
					latitude: 43.5974145,
					longitude: 1.4575596]
		
		assertTrue PersonnelService.getInstance().savePersonnel(new Personnel(myParams))		
	}
    
	void userNotExists() {
		def response = withEndpointRequest(serviceURL) {
			FindNeighboursRequest(xmlns: namespace) {
				FindNeighbours{
					Mail("totoauzoo@univ-tlse3.fr")
					Rayon(42)
				}
			}
		}
		
		assert response.status == "120"
		assert response.message == "Caller could not be found"
	}
	
	void noNeighbours() {
		def response = withEndpointRequest(serviceURL) {
			FindNeighboursRequest(xmlns: namespace) {
				FindNeighbours{
					Mail("totoauzoo@univ-tlse3.fr")
					Rayon(500)
				}
			}
		}
		
		assert response.status == "0"
		assert response.Neighbour.size() == 0
	}
	
	void allNeighbours() {
		def response = withEndpointRequest(serviceURL) {
			FindNeighboursRequest(xmlns: namespace) {
				FindNeighbours{
					Mail("totoauzoo@univ-tlse3.fr")
					Rayon(500)
				}
			}
		}
		
		assert response.status == "0"
		assert response.Neighbour.size() == 3
		
		//TODO check if the neighbour returned are the correct one (paolo, franck, remi)
//		for(int i = 0; i < response.Neighbour.size(); i++){
//			println response.Neighbour[i].Prenom
//			println response.Neighbour[i].Mail
//		}
	}
	
	void someNeighbours() {
		def response = withEndpointRequest(serviceURL) {
			FindNeighboursRequest(xmlns: namespace) {
				FindNeighbours{
					Mail("totoauzoo@univ-tlse3.fr")
					Rayon(50)
				}
			}
		}
		
		assert response.status == "0"
		assert response.Neighbour.size() == 2
		
		//TODO check if the neighbour returned are the correct one (franck, remi)
	}
	
	void noNeighboursInRadius() {
		def response = withEndpointRequest(serviceURL) {
			FindNeighboursRequest(xmlns: namespace) {
				FindNeighbours{
					Mail("totoauzoo@univ-tlse3.fr")
					Rayon(1)
				}
			}
		}
		
		assert response.status == "0"		
		assert response.Neighbour.size() == 0
	}
}