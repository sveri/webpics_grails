package webpics_grails

import webpics_grails.auth.Role
import webpics_grails.auth.User

class PixMailService {

    def mailService

    def grailsApplication


    static MAIL_FROM_ADRESS = "pix@sveri.de"

    def sendNewAlbumMailForRole(Role role) {
        def allUsers = User.list()

        for (User user in allUsers) {
            if (user.roles.contains(role)) {
                sendNewMailForUser(user)
            }
        }
    }

    def sendNewAlbumMailForUser(User user){
        def g = grailsApplication.mainContext.getBean('org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib')

        if(!user.email.isEmpty() && user.receivesUpdates){
            mailService.sendMail {
                to user.email
                from MAIL_FROM_ADRESS
                subject g.message(code: "pix.mail.new_album.subject")
                body g.message(code: "pix.mail.new_album.body") + " " + g.createLink(uri: '/', absolute: "true")
            }
        }
    }
}
