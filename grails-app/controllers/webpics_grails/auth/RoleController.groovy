package webpics_grails.auth

import org.springframework.dao.DataIntegrityViolationException
import webpics_grails.pic.Album

class RoleController {

    def roleService

    def index() {
        [roles: Role.findAll(sort: "name")]
    }

    def add() {
        [role: new Role(), albums: Album.findAll(sort: "name")]
    }

    def edit() {
        [role: Role.get(params.id), albums: Album.findAll(sort: "name")]
    }

    def save() {
        def role = new Role(params)

        roleService.removeUnallowedPermissions(role)

        if (!role.save()) {
            flash.message = message(code: 'pix.save.wrong')
            render(view: "add", model: [role: role, albums: Album.findAll(sort: "name")])
            return
        }
        redirect(action: 'index')
    }

    def update() {
        def role = Role.get(params.id)
//        def albumsOld = role.albums
        if (!role) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'role.label', default: 'Role'), params.id])
            redirect(action: "index")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (role.version > version) {
                role.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'role.label', default: 'Role')] as Object[],
                        "Another user has updated this Role while you were editing")
                render(view: "edit", model: [role: role, albums: Album.all])
                return
            }
        }
//        def oldAlbums = [role.albums]

        role.properties = params

//        roleService.checkIfNewAlbumGotAddedAndSendEmail(role, (String[]) params.albums)

        roleService.removeUnallowedPermissions(role)

        if (!role.save(flush: true)) {
            render(view: "edit", model: [role: role, albums: Album.findAll(sort: "name")])
            flash.message = message(code: 'pix.save.wrong')
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'role.label', default: 'Role'), role.name])
        redirect(action: "index")
    }

    def delete(Long id) {
        def roleInstance = Role.get(id)
        if (!roleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'role.label', default: 'Role'), id])
            redirect(action: "index")
            return
        }

        for(User user in roleInstance.users){
            user.roles.remove(roleInstance)
            user.save()
        }


        try {
            roleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'role.label', default: 'Role'), id])
            redirect(action: "index")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'role.label', default: 'Role'), id])
            redirect(action: "index")
        }
    }
}
