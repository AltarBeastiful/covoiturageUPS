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
	
	void testSetup(){
		PersonnelService.getInstance().setDatabaseName("covoiturageupstest")
		PersonnelService.getInstance().flushDatabase()
	}

    void testList() {

        def model = controller.list()

        assert model.size() == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.personnelInstance != null
    }

    void testSave() {
        def result = controller.save()

		assertFalse result

        response.reset()

        populateValidParams(params)
        result =  controller.save()
		
		assertTrue result
        assert Personnel.count() == 1
    }
}
