package covoiturageups

import javax.sound.midi.ControllerEventListener;
import org.jcouchdb.exception.NotFoundException
import covoiturageups.Personnel

import external.CalculateDistance;

@Grab(group='com.google.code.jcouchdb', module='jcouchdb', version='1.0.1-1')


class FindNeighboursEndpoint {
	
	def static namespace = "http://www.covoiturageups.com/v1/definitions"
		
	def invoke = { request, response ->		
		def controller = new PersonnelController()
		Personnel p1
		
		try{
			p1 = controller.get(request.FindNeighbours.Mail[0].text())
		}catch(NotFoundException){
			println "Caller not found"
			response.FindNeighboursResponse(xmlns: namespace) {
				status('120')
				message('Caller could not be found')
			}
			return
		}		
		double rayon = request.FindNeighbours.Rayon[0].text().toDouble()
		
		List<Personnel> friendlyNeighbours = []
		
		for(Personnel p2 : controller.list(0)){
			if(p1.getEmail() != p2.getEmail()){
				def dist = CalculateDistance.distFrom(p1.getLatitude(), p1.getLongitude(), p2.getLatitude(), p2.getLongitude())
				if(dist <= rayon){
					friendlyNeighbours.add(p2)
				}
			}
		}
		
		//println "Neighbours : " + friendlyNeighbours
		
		response.FindNeighboursResponse(xmlns: namespace) {
			status('0')
			message('Found '+ friendlyNeighbours.size() + ' neighbours')
			
			for (Personnel nb : friendlyNeighbours) {
				Neighbour {
					Prenom(nb.getPrenom())
					Nom(nb.getNom())
					Mail(nb.getEmail())
					Adresse(nb.getAdresse())
				}
			}
		}
    }
}