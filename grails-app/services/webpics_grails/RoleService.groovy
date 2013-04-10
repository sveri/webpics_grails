package webpics_grails

import org.apache.shiro.SecurityUtils

import webpics_grails.auth.Role
import webpics_grails.auth.User
import webpics_grails.pic.Album

class RoleService {

    def grailsApplication

    def userService

    def pixMailService

    def removeUnallowedPermissions(Role role) {
        if (SecurityUtils.subject.hasRole(Role.ADMINISTRATOR)) {
            return
        }

        def allowedPermissions = []

        def loggedInUser = userService.getLoggedInUser()

        def roleLoeggedInIterator = loggedInUser.roles.iterator()

        while (roleLoeggedInIterator.hasNext()) {
            def roleLoggedIn = roleLoeggedInIterator.next()

            def permissionIterator = roleLoggedIn.permissions.iterator()

            while (permissionIterator.hasNext()) {
                def permission = permissionIterator.next()
                allowedPermissions.add(permission)
            }
        }

        def permIterator = role.permissions.iterator()
        while (permIterator.hasNext()) {
            def perm = permIterator.next()
            if (!allowedPermissions.contains(perm)) {
                permIterator.remove()
            }
        }

    }

    def getAllPermissions() {
        return (grailsApplication.controllerClasses.findAll {
            it.propertyName != "authController" && it.propertyName != "dbdocController" && it.propertyName != "errorController"
        }.collect { controller ->
            def base = controller.propertyName - 'Controller'
            controller.getURIs().collect {
                def action = it.split('\\/')
                action = (action.size() == 2 ? "*" : action[2])
                "${base}:${action}"
            }
        } + "*:*").flatten().collect {
            it.toString()
        }.unique().sort()
    }

    def checkIfNewAlbumGotAddedAndSendEmail(Role role, List<Album> albumsOld) {
        def newAlbums = []
        def currentAlbums = role.albums

        for (Album album in currentAlbums) {
            if (!albumsOld.find { it.id == album.id }) {
                newAlbums.add(album)
            }
        }

        if (newAlbums) {
            pixMailService.sendNewAlbumMailForRole(role)
        }
    }

    def checkIfOneAlbumExistsForUserRoles(User user) {
        for (Role role in user.roles) {
            if (!role.albums.isEmpty()) {
                return true
            }
        }
        return false
    }
}
