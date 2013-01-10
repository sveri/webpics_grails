package webpics_grails

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.junit.*

import webpics_grails.auth.Role;
import webpics_grails.auth.User

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@TestFor(UserService)
@Mock([User, Role])
class UserServiceTest {

    def userService

    def secUtil

    void setUp() {
	userService = new UserService()

	secUtil = mockFor(SecurityUtils)
	//	secUtil.demand.static.getSubject {-> [login: {authToken -> true}] }
    }

    void tearDown() {
	// Tear down logic here
    }

    @Test
    void testSomething() {
	def user = new User(
	username: "Admin",
	roles: [
	    new Role(name: "Administrator")
	],
	passwordHash: "aslkfd",
	passwordSalt: "alskfj"
	).save(validate: false)

	secUtil.demand.static.getSubject {
	    -> [hasRole: {
		   String -> true
		}]
	}

	//        secUtil.demand.static.getSubject.hasRole { String role -> return "sdf" }

	//	    def subject = mockFor(Subject)
	//	    subject.demand.hasRole {String role -> null}
	//	    secUtil.demand.static.getSubject.hasRole {-> null }

	//	def mockSubject = [
	//	    	hasRole {
	//			return true
	//		    }
	//	    ] as Subject
	//	secUtil.hasRole("") {-> true }

	//	    def mockSecurityUtils = [
	//		hasRole: { role ->
	//		    hasRoleCalled = true
	//		    Assert.assertEquals("Incorrect role checked.", "Observer", role)
	//		    return false
	//		}
	//	    ] as SecurityUtils


	assert userService.isUserAllowedToSeeAlbum("5")
    }
}
