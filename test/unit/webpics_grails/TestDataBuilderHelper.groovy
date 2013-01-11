package webpics_grails

import grails.buildtestdata.mixin.Build
import webpics_grails.auth.Role
import webpics_grails.auth.User
import webpics_grails.pic.Album


class TestDataBuilderHelper {

    /**
     * sets up three albums, one role "User" and a user with username "sveri".
     * The role user posseses two albums and the third Album does not belong to any role.
     * @return
     */
    static setUpThreeAlbumsAndUserWithRoleUser(){
	def album = Album.build()
	def albumTwo = Album.build()
	Album.build()
	assert Album.count == 3

	def roleUser = Role.build(
	    name: "User",
	    albums: [album, albumTwo]
	)
	User.build(
	    username: "sveri",
	    roles: [ roleUser],
	)
    }

}
