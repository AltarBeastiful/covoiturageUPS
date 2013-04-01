package external
import groovyx.net.http.*

class OpenStreetMapCaller {
	def static String BASE_URL = "http://nominatim.openstreetmap.org"

	def static public Double[] getCoordinatesFromAddress(String address) {
		def http = new HTTPBuilder()

		// perform a GET request, expecting XML response data
		http.request(BASE_URL, Method.GET, ContentType.XML) { req ->
			uri.path = '/search/'
			uri.query = [q: address, format:'xml' ]

			// response handler for a success response code:
			response.success = { resp, xml ->
				
				// parse the XML response object:
				if(xml.place.size() == 0){
					println "No place found"
				}else{	
					if(xml.place.size() > 1){
						println "More than one place found, taking the first one"
					}
					
					def node = xml.place[0] 
					
					return [node.@lat.toDouble(), node.@lon.toDouble()]
				}
			}

			// handler for any failure status code:
			response.failure = { resp ->
				println "Unexpected error: ${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}"
			}
		}
		
	}
}

