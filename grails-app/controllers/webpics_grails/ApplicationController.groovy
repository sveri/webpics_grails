package webpics_grails

class ApplicationController {

    def index() { }
	
	def login() {
		on("submit").to "doLogin"
		
	}
	
	def doLogin() {
		System.out.println("truiane");
	}
}
