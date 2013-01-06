package webpics_grails

import org.apache.shiro.SecurityUtils

import webpics_grails.auth.User
import webpics_grails.pic.Album

class UserService {

    def isUserAllowedToSeeAlbum(String albumId){
	def album = Album.get(albumId)
	def user = getLoggedInUser()
	def retVal = false

	for (role in user.roles){
	    for(rAlbum in role.albums){

    	    	if(rAlbum.id == album.id){
                    retVal = true
                    break
		}
	    }
	}

	return retVal
    }

    def listAllAlbumsUserIsAllowedToSee(){
	def user = getLoggedInUser()
	def albums = []

	for (role in user.roles){
	    for(album in role.albums){
		albums.add(album)
	    }
	}
	return albums
    }

    def getLoggedInUser(){
	return User.findByUsername(SecurityUtils.getSubject().principal)
    }
}
