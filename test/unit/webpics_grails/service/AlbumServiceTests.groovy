package webpics_grails.service


import grails.buildtestdata.mixin.Build
import grails.plugin.spock.UnitSpec
import grails.test.mixin.TestFor

import org.apache.shiro.SecurityUtils
import org.junit.*

import spock.lang.Specification

import webpics_grails.AlbumService
import webpics_grails.TestDataBuilderHelper
import webpics_grails.UserService
import webpics_grails.auth.Role
import webpics_grails.auth.User
import webpics_grails.pic.Album

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(AlbumService)
@Build([User, Role, Album])
class AlbumServiceTests {

    AlbumService albumService = new AlbumService()

    @Test
    void testAddAlbumToLoggedinUser() {
	TestDataBuilderHelper.setUpThreeAlbumsAndUserWithRoleUser()
	AlbumService albumService = new AlbumService()

	def albumNewOne = Album.build(name: "aaaa")
	def albumNewTwo = Album.build(name: "bbb")
	def roleTwo = Role.build(name: "roleTwo")
	def roleThree = Role.build(name: "roleThree")
	def sveri = User.findByUsername("sveri")
	sveri.addToRoles(roleTwo)


	def userService = mockFor(UserService)
	userService.demand.getLoggedInUser(1..4) { -> sveri}
	albumService.userService = userService.createMock()

	albumService.addAlbumToLoggedInUserRoles(albumNewOne)
	albumService.addAlbumToLoggedInUserRoles(albumNewTwo)

	assert roleTwo.albums.size() == 2

	sveri.addToRoles(roleThree)
	albumService.addAlbumToLoggedInUserRoles(albumNewOne)

	assert roleTwo.albums.size() == 2
	assert roleThree.albums.size() == 1
    }
}
