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
            userRole.addToPermissions("album:album")
            userRole.addToPermissions("album:index")
            userRole.addToPermissions("album:getFile")
            userRole.addToPermissions("album:jsupload")
            userRole.addToPermissions("album:save")
            userRole.addToPermissions("album:upload")
            userRole.addToPermissions("album:zipupload")
            userRole.save()

            def viewerRole = new Role(name: "Viewer")
            viewerRole.addToPermissions("album:album")
            viewerRole.addToPermissions("album:index")
            viewerRole.addToPermissions("album:getFile")
            viewerRole.save()

            def passwordSalt = new SecureRandomNumberGenerator().nextBytes().getBytes()
            def admin = new User(username:"admin",passwordHash: new Sha512Hash("admin",passwordSalt,1024).toBase64(),passwordSalt:passwordSalt)
            admin.addToRoles(adminRole)
            admin.save()
        }
    }

    def destroy = {
    }
}
