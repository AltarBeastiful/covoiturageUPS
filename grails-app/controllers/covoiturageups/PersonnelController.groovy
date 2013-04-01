package covoiturageups

import covoiturageups.PersonnelService

import org.springframework.dao.DataIntegrityViolationException

class PersonnelController {
	def service = PersonnelService.getInstance()

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
//        params.max = Math.min(max ?: 10, 100)
//        [personnelInstanceList: Personnel.list(params), personnelInstanceTotal: Personnel.count()]
		return service.getAllPersonnel()
        //[personnelInstanceList: service.getAllPersonnel(), personnelInstanceTotal: perosnnelInstaceList.size()]
    }

    def create() {
        [personnelInstance: new Personnel(params)]
    }

    def save() {
        def personnelInstance = new Personnel(params)
        if (!personnelInstance.save(flush: true)) {
            render(view: "create", model: [personnelInstance: personnelInstance])
            return false
        }
		
		return service.savePersonnel(personnelInstance)
		
    }

//    def show(Long id) {
//        def personnelInstance = Personnel.get(id)
//        if (!personnelInstance) {
//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'personnel.label', default: 'Personnel'), id])
//            redirect(action: "list")
//            return
//        }
//
//        [personnelInstance: personnelInstance]
//    }
//
//    def edit(Long id) {
//        def personnelInstance = Personnel.get(id)
//        if (!personnelInstance) {
//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'personnel.label', default: 'Personnel'), id])
//            redirect(action: "list")
//            return
//        }
//
//        [personnelInstance: personnelInstance]
//    }
//
//    def update(Long id, Long version) {
//        def personnelInstance = Personnel.get(id)
//        if (!personnelInstance) {
//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'personnel.label', default: 'Personnel'), id])
//            redirect(action: "list")
//            return
//        }
//
//        if (version != null) {
//            if (personnelInstance.version > version) {
//                personnelInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
//                          [message(code: 'personnel.label', default: 'Personnel')] as Object[],
//                          "Another user has updated this Personnel while you were editing")
//                render(view: "edit", model: [personnelInstance: personnelInstance])
//                return
//            }
//        }
//
//        personnelInstance.properties = params
//
//        if (!personnelInstance.save(flush: true)) {
//            render(view: "edit", model: [personnelInstance: personnelInstance])
//            return
//        }
//
//        flash.message = message(code: 'default.updated.message', args: [message(code: 'personnel.label', default: 'Personnel'), personnelInstance.id])
//        redirect(action: "show", id: personnelInstance.id)
//    }
//
//    def delete(Long id) {
//        def personnelInstance = Personnel.get(id)
//        if (!personnelInstance) {
//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'personnel.label', default: 'Personnel'), id])
//            redirect(action: "list")
//            return
//        }
//
//        try {
//            personnelInstance.delete(flush: true)
//            flash.message = message(code: 'default.deleted.message', args: [message(code: 'personnel.label', default: 'Personnel'), id])
//            redirect(action: "list")
//        }
//        catch (DataIntegrityViolationException e) {
//            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'personnel.label', default: 'Personnel'), id])
//            redirect(action: "show", id: id)
//        }
//    }
}
