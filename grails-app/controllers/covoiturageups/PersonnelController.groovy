package covoiturageups

import covoiturageups.PersonnelService

import org.springframework.dao.DataIntegrityViolationException

class PersonnelController {
	def service = PersonnelService.getInstance()

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list(Integer max) {
//        params.max = Math.min(max ?: 10, 100)
//        [personnelInstanceList: Personnel.list(params), personnelInstanceTotal: Personnel.count()]
		return service.getAllPersonnel()
//        [personnelInstanceList: service.getAllPersonnel(), personnelInstanceTotal: perosnnelInstaceList.size()]
    }

    def create() {
        [personnelInstance: new Personnel(params)]
    }

    def save() {
        def personnelInstance = new Personnel(params)
		Personnel.withTransaction {
	        if (!personnelInstance.save(flush: true)) {
	            //render(view: "create", model: [personnelInstance: personnelInstance])
	            return false
	        }
        
	
			return service.savePersonnel(personnelInstance)
		
        }
    }
	
	def get(String email){
		return service.getPersonnel(email)
	}
}
