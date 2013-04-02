package covoiturageups

import org.jcouchdb.exception.NotFoundException
import covoiturageups.Personnel
import covoiturageups.PersonnelController

import org.junit.*
import grails.test.mixin.*

@Grab(group='com.google.code.jcouchdb', module='jcouchdb', version='1.0.1-1')

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
	
	@Before
	@Test
	void testSetup(){
		PersonnelService.getInstance().setDatabaseName("covoiturageupstest")
		PersonnelService.getInstance().flushDatabase()
	}

	@Test
    void testList() {

        def model = controller.list()

        assert model.size() == 0
    }

	@Test
    void testCreate() {
        def model = controller.create()

        assert model.personnelInstance != null
    }

	@Test
    void testSave() {
        def result = controller.save()

		assertFalse result

        response.reset()

        populateValidParams(params)
        result =  controller.save()
		
		assertTrue result
        assert Personnel.count() == 1
    }
	
	@Test
	void testGet(){
		
		shouldFail(NotFoundException) {
			def res = controller.get("undefined@rien.com")
		}
		
        params["nom"] = 'francj'
		params["prenom"] = 'bien'
		params["email"] = 'franck@univ-tlse3.fr'
		params["adresse"] = '3 rue des bien'
		params["latitude"] = 1.45
		params["longitude"] = 1.46	
		
		assertTrue controller.save()
		
		Personnel res = controller.get(params["email"])
		
		//TODO implement Personnel comparaison
		assert res.getEmail() == params["email"]
		assert res.getNom() == params["nom"]
	}
	
	
}
