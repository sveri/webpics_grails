package webpics_grails

import webpics_grails.auth.User
import webpics_grails.pic.Album

class AlbumService {

    def userService

    def addAlbumToLoggedInUserRoles(Album album) {
	User user = userService.getLoggedInUser()
	for(role in user.roles){
	    role.addToAlbums(album)
	}
    }
}
