package covoiturageups



class FindNeighboursEndpoint {
	
	def static namespace = "http://www.covoiturageups.com/v1/definitions"
		
	def invoke = { request, response ->
		// Using the incoming document
		println "Neighbour Request Received!"
		println "Mail: ${request.FindNeighbours.Mail[0].text()}"
		println "Rayon: ${request.FindNeighbours.Rayon[0].text()}"
		
		
		response.FindNeighboursResponse(xmlns: namespace) {
		  status('complete')
		}
    }
}