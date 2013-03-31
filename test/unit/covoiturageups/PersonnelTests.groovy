package covoiturageups

import grails.test.mixin.*
import covoiturageups.Personnel
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Personnel)
class PersonnelTests {

	void testConstraints() {
		// TODO : fix this instance
		def p1 = new Personnel( prenom: "Remi", nom: "Benoit", email: "me@univ-tlse3.fr", adresse: "4 rue des hommes")

		mockForConstraintsTests(Personnel, [p1])
		
		assert p1.validate()
		
		def p2 = new Personnel ()
		
		assertFalse p2.validate()
		assertEquals "nullable", p2.errors["prenom"]
		assertEquals "nullable", p2.errors["nom"]
		assertEquals "nullable", p2.errors["email"]
		assertEquals "nullable", p2.errors["adresse"]
		
		def p3 = new Personnel (prenom: "", nom: "" , email: "", adresse: "")
		
		assertFalse p3.validate()
		assertEquals "blank", p3.errors["prenom"]
		assertEquals "blank", p3.errors["nom"]
		assertEquals "blank", p3.errors["email"]
		assertEquals "blank", p3.errors["adresse"]
		
		def p4 = new Personnel (prenom: "test1", nom: "boua" , email: "remmm", adresse: "me")
		
		assertFalse p4.validate()
		assertEquals "email", p4.errors["email"]
		
		def p5 = new Personnel (prenom: "test1", nom: "boua" , email: "me@truc.com", adresse: "rien")
		
		assertFalse p5.validate()
		assertEquals "matches", p5.errors["email"]
		
	}
}
