package webpics_grails

class ApplicationController {

	def index() {
	}

	def login() {
		[loginCommand: new LoginCommand()]
	}

	def doLogin(LoginCommand cmd) {
		withForm {
			if (cmd.hasErrors()) {
				redirect(action: 'login')	
			}
			System.out.println("truiane");
		}
	}
}

@grails.validation.Validateable
class LoginCommand {
    String username
    String password
	
	

	static constraints = { 
		username(blank: false, minSize: 6) 
		password(blank: false, minSize: 6) 
	} 
}
