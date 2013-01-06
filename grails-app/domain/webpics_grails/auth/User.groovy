package webpics_grails.auth

import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject
import webpics_grails.pic.Album

class User {
    String username
    String passwordHash
    byte[] passwordSalt

    static hasMany = [ roles: Role, permissions: String]

    static constraints = {
	username(nullable: false, blank: false, unique: true)
    }

    String toString(){
	return username
    }
}
