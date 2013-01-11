package webpics_grails.service

import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import grails.test.mixin.support.*

import org.apache.shiro.SecurityUtils
import org.junit.Test

import webpics_grails.TestDataBuilderHelper
import webpics_grails.UserService
import webpics_grails.auth.Role
import webpics_grails.auth.User
import webpics_grails.pic.Album

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@TestFor(UserService)
@Build([User, Role, Album])
class UserServiceTest {

    UserService userService

    def secUtil

    void setUp() {
	userService = new UserService()
	secUtil = mockFor(SecurityUtils)
    }

    @Test
    void testAdminIsAllowedToSeeEveryAlbum() {
	secUtil.demand.static.getSubject(1..2) {
	    -> [hasRole: { String -> true }]
	}

	assert userService.isUserAllowedToSeeAlbum("")
	assert userService.isUserAllowedToSeeAlbum("5")
    }

    @Test
    void testIsAllowedToSeeHisAlbum() {
	TestDataBuilderHelper.setUpThreeAlbumsAndUserWithRoleUser()
	secUtil.demand.static.getSubject(1..6) {
	    -> [hasRole: { String -> false }
		,getPrincipal: { -> "sveri"}
	    ]
	}

	assert userService.isUserAllowedToSeeAlbum("1")
	assert userService.isUserAllowedToSeeAlbum("2")
	assert ! userService.isUserAllowedToSeeAlbum("3")
    }

    @Test
    void testListAllAlbumsForUser() {
	TestDataBuilderHelper.setUpThreeAlbumsAndUserWithRoleUser()

	secUtil.demand.static.getSubject(1..2) {
	    -> [hasRole: { String -> false }
		,getPrincipal: { -> "sveri"}
	    ]
	}

	assert userService.listAllAlbumsUserIsAllowedToSee().size() == 2
    }

    @Test
    void testListAllAlbumsForAdmin() {
	TestDataBuilderHelper.setUpThreeAlbumsAndUserWithRoleUser()

	secUtil.demand.static.getSubject(1..2) {
	    -> [hasRole: { String -> true }]
	}

	assert userService.listAllAlbumsUserIsAllowedToSee().size() == 3
    }
}
