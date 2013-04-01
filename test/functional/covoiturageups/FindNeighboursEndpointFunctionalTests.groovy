package covoiturageups

import org.codehaus.groovy.grails.plugins.spring.ws.EndpointFunctionalTestCase

class FindNeighboursEndpointFunctionalTests extends EndpointFunctionalTestCase {
	
	def serviceURL = "http://localhost:8080/covoiturageUPS/services/v1/FindNeighbours"
	def namespace = "http://www.covoiturageups.com/v1/definitions"

    void setUp(){
      super.setUp()
      webServiceTemplate.setDefaultUri(serviceURL)
    }
    
	void testSOAPDocumentService() {
		// OK Test
		def response = withEndpointRequest(serviceURL) {
			FindNeighboursRequest(xmlns: namespace) {
				FindNeighbours{
					Mail("totoauzoo@univ-tlse3.fr")
					Rayon(42)
				}
			}
		}
		
		
		def status = response.status
		assert status == "complete"
	}
}