package webpics_grails.auth

class RoleController {

    def index() {
	[roles: Role.all]
    }

    def add() {
	[role: new Role()]
    }

    def edit() {
    }
}
