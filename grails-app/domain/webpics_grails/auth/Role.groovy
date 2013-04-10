package webpics_grails.auth

import org.apache.shiro.SecurityUtils
import webpics_grails.pic.Album

class Role {
    def userService

    String name

    static ADMINISTRATOR = 'Administrator'

    static hasMany = [users: User, permissions: String, albums: Album]
    static belongsTo = User

    static mapping = {
        sort: "name"
        albums cascade: "save-update"
    }

    static constraints = {
        name(nullable: false, blank: false, unique: true)
    }

    String toString() {
        return name
    }

    static listAvailableRoles() {
        if (SecurityUtils.subject.hasRole(ADMINISTRATOR)) {
            return listOrderByName()
        }

        return User.findByUsername(SecurityUtils.subject.getPrincipal() as String).getRoles().sort()
    }
}
