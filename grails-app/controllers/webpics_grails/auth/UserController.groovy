package webpics_grails.auth

import grails.validation.Validateable;

import org.apache.shiro.crypto.SecureRandomNumberGenerator
import org.apache.shiro.crypto.hash.Sha512Hash
import org.springframework.dao.DataIntegrityViolationException

class UserController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST", changePassword: "POST"]

    def userService

	def index() {
		[users: User.findAll(sort: "username")]
	}

	def create() {
		[user: new User(params), passwordCommand: new PasswordCommand()]
	}

	def save() {
		def userInstance = new User(params)
		def passwordCommand = new PasswordCommand()

        //remove unallowed roles
        userService.removeUnallowedRoles(userInstance)

		//set blind values for password
		userInstance.passwordHash = "bla"
		userInstance.passwordSalt = "bla"
		if(!userInstance.validate()){
			flash.message = message(code: 'pix.user.add_edit_error')
			render(view: "create", model: [user: userInstance, passwordCommand: passwordCommand])
			return
		}

		bindData(passwordCommand, params)
		if(!passwordCommand.validate()){
			flash.message = message(code: 'user.password.not_null_and_match')
			render(view: "create", model: [user: userInstance, passwordCommand: passwordCommand])
			return
		}

		//		if user id is null we create a new user and have to generate a password + hash
		if(!userInstance.id){
			setPassword(userInstance, params.password)
		}

		if (!userInstance.save(flush: true)) {
			render(view: "create", model: [user: userInstance, passwordCommand: new PasswordCommand()])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'pix.user', default: 'User'), userInstance.username])
		redirect(action: "index")
	}


	def setPassword(User userInstance, String password) {
		def passwordSalt = new SecureRandomNumberGenerator().nextBytes().getBytes()
		userInstance.passwordSalt = passwordSalt
		userInstance.passwordHash = new Sha512Hash(password,passwordSalt,1024).toBase64()
	}

	def edit(Long id) {
		def userInstance = User.get(id)
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'pix.user', default: 'User'), id])
			redirect(action: "index")
			return
		}

		[user: userInstance, passwordCommand: new PasswordCommand()]
	}

	def changePassword(Long id){
		def userInstance = User.get(id)
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'pix.user', default: 'User'), id])
			redirect(action: "index")
			return
		}

		def passwordCommand = new PasswordCommand()
		bindData(passwordCommand, params)

		if(!passwordCommand.validate()){
			flash.message_password = message(code: 'user.password.not_null_and_match')
			render(view: "edit", model: [user: userInstance, passwordCommand: passwordCommand])
			return
		}

		setPassword(userInstance, params.password)

		flash.message_password = message(code: 'user.password.successfully_changed')

		redirect(action: "edit", id: userInstance.id)
	}

	def update(Long id, Long version) {
		def userInstance = User.get(id)
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'pix.user', default: 'User'), id])
			redirect(action: "index")
			return
		}

		if (version != null) {
			if (userInstance.version > version) {
				userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[message(code: 'pix.user', default: 'User')] as Object[],
						"Another user has updated this User while you were editing")
				render(view: "edit", model: [user: userInstance], passwordCommand: new PasswordCommand())
				return
			}
		}

		//        userInstance.properties = params
		bindData(userInstance, params, [include: ['username', 'roles', 'email', 'receivesUpdates']])

        //remove unallowed roles
        userService.removeUnallowedRoles(userInstance)

		if (!userInstance.save(flush: true)) {
			render(view: "edit", model: [user: userInstance], passwordCommand: new PasswordCommand())
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'pix.user', default: 'User'), userInstance.username])
		redirect(action: "edit", id: userInstance.id)
	}

	def delete(Long id) {
		def userInstance = User.get(id)
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'pix.user', default: 'User'), id])
			redirect(action: "index")
			return
		}

		try {
			userInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'pix.user', default: 'User'), id])
			redirect(action: "index")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'pix.user', default: 'User'), id])
			redirect(action: "index")
		}
	}
}

@Validateable
class PasswordCommand {
	String password
	String passwordRepeat

	static constraints = {
		passwordRepeat blank: false, minSize: 6
		password blank: false, minSize: 6, validator: {password, obj ->
			def password2 = obj.properties['passwordRepeat']
			password2 == password ? true: ['pix.user.password.dont_match']
		}
	}
}
