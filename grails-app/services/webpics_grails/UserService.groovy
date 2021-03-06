package webpics_grails

import org.apache.shiro.SecurityUtils
import webpics_grails.auth.Role
import webpics_grails.auth.User
import webpics_grails.pic.Album

class UserService {

    def isUserAllowedToSeeAlbum(String albumId) {
        if (SecurityUtils.subject.hasRole(Role.ADMINISTRATOR)) {
            return true
        }

        def album = Album.get(albumId)
        def user = getLoggedInUser()

        def retVal = false

        for (role in user.roles) {
            for (rAlbum in role.albums) {

                if (rAlbum.id == album.id) {
                    retVal = true
                    break
                }
            }
        }

        return retVal
    }

    def listAllAlbumsUserIsAllowedToSee() {
        if (SecurityUtils.subject.hasRole(Role.ADMINISTRATOR)) {
            return Album.list()
        }

        def user = getLoggedInUser()
        def albums = []

        for (role in user.roles) {
            for (album in role.albums) {
                albums.add(album)
            }
        }
        return albums.sort()
    }

    def removeUnallowedRoles(User user){
        if (SecurityUtils.subject.hasRole(Role.ADMINISTRATOR)) {
            return
        }

        def loggedInUser = getLoggedInUser()

        def roleIterator = user.roles.iterator()
        while(roleIterator.hasNext()){
            def role = roleIterator.next()
            if (!loggedInUser.roles.contains(role)){
                roleIterator.remove()
            }
        }
    }

    static getLoggedInUser() {
        return User.findByUsername(SecurityUtils.subject.getPrincipal() as String)
    }

    static getLoggedinUsersPermissions() {
        def user = getLoggedInUser()
        def permissions = []
        user.roles.each { role ->
            role.permissions.each { perm ->
                permissions << perm
            }
        }
        return permissions.unique().sort()
    }

    def checkIfNewRoleGotAddedToUser(User user, List<Role> rolesOld) {
        for (Role roleNew in user.roles) {
            if (!rolesOld.find { it.id == roleNew.id }) {
                return true
            }
        }
        return false
    }
}
