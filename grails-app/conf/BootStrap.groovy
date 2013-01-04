import org.apache.shiro.crypto.SecureRandomNumberGenerator
import org.apache.shiro.crypto.hash.Sha512Hash

import webpics_grails.auth.Role
import webpics_grails.auth.User

class BootStrap {

    def init = { servletContext ->

	if (User.count == 0) {
	    def adminRole = new Role(name: "Administrator")
	    adminRole.addToPermissions("*:*")
	    adminRole.save()

	    def userRole = new Role(name:"User")
	    addViewerPermissions(userRole)
	    addUserPermissions(userRole)
	    userRole.save()

	    def viewerRole = new Role(name: "Viewer")
	    addViewerPermissions(viewerRole)
	    viewerRole.save()

	    def passwordSalt = new SecureRandomNumberGenerator().nextBytes().getBytes()
	    def admin = new User(username:"admin",passwordHash: new Sha512Hash("admin",passwordSalt,1024).toBase64(),passwordSalt:passwordSalt)
	    admin.addToRoles(adminRole)
	    admin.save()
	}
    }

    def addUserPermissions(Role role){
	role.addToPermissions("album:jsupload")
	role.addToPermissions("album:save")
	role.addToPermissions("album:upload")
	role.addToPermissions("album:zipupload")
    }

    def addViewerPermissions(Role role){
	role.addToPermissions("album:album")
	role.addToPermissions("album:index")
	role.addToPermissions("album:getFile")
	role.addToPermissions("album:downloadAlbum")
    }

    def destroy = {
    }
}
