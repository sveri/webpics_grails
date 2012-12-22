import org.apache.shiro.crypto.SecureRandomNumberGenerator
import org.apache.shiro.crypto.hash.Sha512Hash

import webpics_grails.auth.Role
import webpics_grails.auth.User

class BootStrap {

    def init = { servletContext ->
//		def adminRole = new Role(name: "Administrator")
//		adminRole.addToPermissions("*:*")
//		adminRole.save()
//	   
//		def userRole = new Role(name:"User")
//		userRole.addToPermissions("Home:index")
//		userRole.save()
//		
//		def viewerRole = new Role(name: "Viewer")
//		viewerRole.addToPermissions("*:*")
//		viewerRole.save()
//		
//		def passwordSalt = new SecureRandomNumberGenerator().nextBytes().getBytes()
//		def admin = new User(username:"admin",passwordHash: new Sha512Hash("zzzzzz",passwordSalt,1024).toBase64(),passwordSalt:passwordSalt)
//		admin.addToRoles(adminRole)
//		admin.save()
    }
	
    def destroy = {
    }
}
