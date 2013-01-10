package webpics_grails

import grails.test.mixin.support.*

import org.apache.shiro.SecurityUtils

import webpics_grails.auth.Role
import webpics_grails.auth.User
import webpics_grails.pic.Album

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@TestFor(UserService)
@Mock([User, Role, Album])
class UserServiceTest {

    def userService

    def secUtil

    void setUp() {
	userService = new UserService()

	secUtil = mockFor(SecurityUtils)
	//	secUtil.demand.static.getSubject {-> [login: {authToken -> true}] }
    }


    //    @Test
    void testAdminIsAllowedToSeeEveryAlbum() {

	secUtil.demand.static.getSubject(1..2) {
	    -> [hasRole: { String -> true }]
	}

	assert userService.isUserAllowedToSeeAlbum("")
	assert userService.isUserAllowedToSeeAlbum("5")
    }

    @Test
    void testIsAllowedToSeeHisAlbum() {
	def user = new User(
	username: "sveri",
	roles: [
	    new Role(name: "User")
	],
	passwordHash: "aslkfd",
	passwordSalt: "alskfj"
	).save(validate: false)

	def album = new Album(name: "bla", id: 1).save(validate: false)

	secUtil.demand.static.getSubject(1..99) { -> [hasRole: { String -> false }
	    , getPrincipal: {-> "sveri"}
	    ]
	}
	//	secUtil.demand.static.getSubject { -> [principal: { -> "sveri"} ]}

	assert userService.isUserAllowedToSeeAlbum("1")

    }



    void tearDown() {
	// Tear down logic here
    }
}
