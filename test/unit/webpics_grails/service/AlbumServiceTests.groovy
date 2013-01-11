package webpics_grails.service


import grails.buildtestdata.mixin.Build
import grails.test.mixin.*
import org.junit.*

import webpics_grails.AlbumService
import webpics_grails.auth.Role
import webpics_grails.auth.User
import webpics_grails.pic.Album

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(AlbumService)
@Build([User, Role, Album])
class AlbumServiceTests {

    @Test
    void testAddAlbumToLoggedinUser() {
	UserServiceTest.setUpAlbumsAndUser()
    }
}
