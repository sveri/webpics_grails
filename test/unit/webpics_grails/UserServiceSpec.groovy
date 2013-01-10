package webpics_grails



import org.apache.shiro.SecurityUtils;
import org.junit.Test;

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import webpics_grails.auth.Role;
import webpics_grails.auth.User
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(UserService)
@Mock([User, Role, SecurityUtils])
class UserServiceSpec extends Specification {

    def "let's try this!"() {
	given:
	def user = new User(
		username: "Admin",
		roles: [
		    new Role(name: "Administrator")
		    ],
		passwordHash: "aslkfd",
		passwordSalt: "alskfj"
		).save(validate: false)

	expect:
	User.count == 1
    }
    //    def "Admin should be allowed to see every album"() {
    //	given:
    //	def user = new User(
    //		username: "Admin",
    //		passwordHash: "aslkfd",
    //		passwordSalt: "alskfj"
    //		).save(validate: false)
    //	//	    new User(
    //	//		    login: "geoff",
    //	//		    permissions: ["plugin:publish:tomcat", "plugin:view"],
    //	//		    roles: [
    //	//		            new Role(name: "editor", permissions: ["wiki:view,edit"]),
    //	//		            new Role(name: "guest", permissions: ["wiki:view"])
    //	//		            ],
    //	//		    ).save(validate: false)
    //	//		    new User(
    //	//			    login: "andy",
    //	//			    permission: ["plugin:view", "plugin:update"],
    //	//			    roles: [new Role(name: "observer", permissions: ["wiki:view", "news:view"])],
    //	//			    ).save(validate: false)
    //	//			    def userService = new UserService()
    //
    //	when:
    //	def userService = new UserService()
    //	//	    userService.
    //
    //	then:
    //	3 == 3
    //	//	permissions.size() == 4
    //	//	permissions.contains "plugin:publish:tomcat"
    //	//	permissions.contains "plugin:view"
    //	//	permissions.contains "wiki:view,edit"
    //	//	permissions.contains "wiki:view"
    //    }
    //    def fixtureLoader
    //
    //    def grailsApplication
    //
    //    void setUp(){
    //    }

    //    void testAdminShouldBeAllowedToSeeAllAlbums() {
    //
    //	//	def fixtureLoader = new FixtureLoader(grailsApplication)
    //	//	def user = fixtureLoader.load {
    //	//	    sveri(username: "Admin", roles: "Administrator", passwordHash: "aslkfd", passwordSalt: "alskfj")
    //	//	}
    //	//	//	fixtureLoader.load("users")
    //	//	def admin = new User(username: "Admin", roles: "Administrator", passwordHash: "aslkfd", passwordSalt: "alskfj").save()
    //	//	mockDomain(User, [
    //	//		    [username: "Admin", roles: "Administrator", passwordHash: "aslkfd", passwordSalt: "alskfj"]
    //	//		])
    //	//	//	//        service.isUserAllowedToSeeAlbum()
    //	//	assert User.count == 1
    //    }

}

