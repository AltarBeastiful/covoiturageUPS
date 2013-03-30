package external
import groovyx.net.http.*

class OpenStreetMapCaller {
	def static String BASE_URL = "nominatim.openstreetmap.org"

	def static public Double[] getCoordinatesFromAddress(String address) {
		def http = new HTTPBuilder(BASE_URL)

		// perform a GET request, expecting XML response data
		http.get(path :'/search',
			contentType : ContentType.XML,
			query : [q: address, format:'xml' ] ) 
		{ response, reader ->

			// response handler for a success response code:
			response.success = { resp, xml ->
				println resp.statusLine

				// parse the XML response object:

				xml.responseData.results.each {
					println "  ${it.titleNoFormatting} : ${it.visibleUrl}"
				}
			}

			// handler for any failure status code:
			response.failure = { resp ->
				println "Unexpected error: ${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}"
			}
		}
		
	}
}

