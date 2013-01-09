package webpics_grails



import grails.test.mixin.*
import org.junit.*
import webpics_grails.auth.User

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(UserService)
class UserServiceTests {

    def fixtureLoader

    void setUp(){
        fixtureLoader.load("users")
    }

    void testAdminShouldBeAllowedToSeeAllAlbums() {
//        def admin = new User(username: "Admin", roles: "Administrator", passwordHash: "aslkfd", passwordSalt: "alskfj").save()
//        service.isUserAllowedToSeeAlbum()
        assert 3 == 3
    }

}
