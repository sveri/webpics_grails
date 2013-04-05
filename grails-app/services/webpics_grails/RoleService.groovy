package webpics_grails

import org.apache.shiro.SecurityUtils
import org.codehaus.groovy.grails.commons.ApplicationHolder
import webpics_grails.auth.Role
import webpics_grails.auth.User

class RoleService {

    def grailsApplication

    def userService

    def mailService

    static MAIL_FROM_ADRESS = "pix@sveri.de"

    def removeUnallowedPermissions(Role role){
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
        while(permIterator.hasNext()){
            def perm = permIterator.next()
            if (!allowedPermissions.contains(perm)){
                permIterator.remove()
            }
        }

    }

    def getAllPermissions() {
        return ( grailsApplication.controllerClasses.findAll {
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

    def checkIfNewAlbumGotAddedAndSendEmail(Role role, Set albumsOld) {
//        def roleOld
        def currentAlbums = role.albums.minus(albumsOld)
        def g = grailsApplication.mainContext.getBean('org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib')

        if(role.id){
//            roleOld = Role.get(role.id)
//            currentAlbums = currentAlbums.minus(roleOld.albums)

            if(currentAlbums){
                def allUsers = User.all
                for(User user in allUsers){
                    if(user.roles.contains(roleOld) && !user.email.isEmpty()){
                        mailService.sendMail {
                            to user.email
                            from MAIL_FROM_ADRESS
                            subject g.message(code: "pix.mail.new_album.subject")
                            body g.message(code: "pix.mail.new_album.body") + " " + g.createLink(uri: '/', absolute: "true")+ currentAlbums
                        }
                    }
                }
            }


        }




    }
}
