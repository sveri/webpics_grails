package webpics_grails.auth

import webpics_grails.pic.Album

class RoleController {

    def index() {
	[roles: Role.all]
    }

    def add() {
	    [role: new Role(), albums: Album.all]
    }

    def edit() {
	    [role: Role.get(params.id), albums: Album.all]
    }

    def save() {
        def role = new Role(params)
        if(!role.save()){
            flash.message = message(code: 'pix.save.wrong')
            render(view: "add", model: [role: role, albums: Album.all])
            return
        }
        redirect(action: 'index')
    }

    def update(){
        def role = Role.get(params.id)
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

        role.properties = params

        if (!role.save(flush: true)) {
            render(view: "edit", model: [role: role, albums: Album.all])
            flash.message = message(code: 'pix.save.wrong')
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'role.label', default: 'Role'), role.id])
        redirect(action: "index")
    }
}
