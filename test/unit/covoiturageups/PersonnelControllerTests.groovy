package covoiturageups

import covoiturageups.Personnel
import covoiturageups.PersonnelController

import org.junit.*
import grails.test.mixin.*

@TestFor(PersonnelController)
@Mock(Personnel)
class PersonnelControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        params["nom"] = 'remi'
		params["prenom"] = 'dfdsfs'
		params["email"] = 'remi@univ-tlse3.fr'
		params["adresse"] = '3 rue des trucs'
		params["latitude"] = 1.42
		params["longitude"] = 1.43
    }

    void testIndex() {
        controller.index()
        assert "/personnel/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.personnelInstanceList.size() == 0
        assert model.personnelInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.personnelInstance != null
    }

    void testSave() {
        controller.save()

        assert model.personnelInstance != null
        assert view == '/personnel/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        //assert response.redirectedUrl == '/personnel/show/1'
        //assert controller.flash.message != null
        assert Personnel.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/personnel/list'

        populateValidParams(params)
        def personnel = new Personnel(params)

        assert personnel.save() != null

        params.id = personnel.id

        def model = controller.show()

        assert model.personnelInstance == personnel
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/personnel/list'

        populateValidParams(params)
        def personnel = new Personnel(params)

        assert personnel.save() != null

        params.id = personnel.id

        def model = controller.edit()

        assert model.personnelInstance == personnel
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/personnel/list'

        response.reset()

        populateValidParams(params)
        def personnel = new Personnel(params)

        assert personnel.save() != null

        // test invalid parameters in update
        params.id = personnel.id
		params.email = "gfdsf"
        //TODO: add invalid values to params object
		
        controller.update()

        //assert view == "/personnel/edit"
        assert model.personnelInstance != null

        personnel.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/personnel/show/$personnel.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        personnel.clearErrors()

        populateValidParams(params)
        params.id = personnel.id
        params.version = -1
        controller.update()

        assert view == "/personnel/edit"
        assert model.personnelInstance != null
        assert model.personnelInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/personnel/list'

        response.reset()

        populateValidParams(params)
        def personnel = new Personnel(params)

        assert personnel.save() != null
        assert Personnel.count() == 1

        params.id = personnel.id

        controller.delete()

        assert Personnel.count() == 0
        assert Personnel.get(personnel.id) == null
        assert response.redirectedUrl == '/personnel/list'
    }
}
