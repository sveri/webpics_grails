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

    static def isAllowed(String albumId){
        Subject subject = SecurityUtils.getSubject()
        def user = User.findByUsername("sveri_user")
//        def user = User.findByUsername(subject.principal)
        def retVal = user.roles.find { role ->
            return role?.albums?.find { album ->
                if (album.id == albumId){
                    return true
                }
            }
            return false
        }
        println retVal
        return retVal

    }
}
