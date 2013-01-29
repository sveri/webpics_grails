package webpics_grails

import webpics_grails.auth.User
import webpics_grails.pic.Album
import webpics_grails.pic.Photo

import java.nio.file.Files
import java.nio.file.Paths

class AlbumService {

    def userService

    def pictureService

    def addAlbumToLoggedInUserRoles(Album album) {
        User user = userService.getLoggedInUser()
        for (role in user.roles) {
            role.addToAlbums(album)
        }
    }

    def deleteAlbum(Album album) throws Exception {
        def photos = Photo.findAllByAlbum(album)

        for (role in album.roles) {
            role.albums.remove(album)
        }


        for (photo in photos) {
            photo.delete(flush: true)
        }

        deleteAlbumFromDisk(album)
        album.delete(flush: true)
    }

    def deleteAlbumFromDisk(Album album) {
        return new File(pictureService.getAlbumBasePath(album.id.toString())).deleteDir()
    }
}
