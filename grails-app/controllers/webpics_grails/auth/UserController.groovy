package webpics_grails.auth

import grails.validation.Validateable;

import org.apache.shiro.crypto.SecureRandomNumberGenerator
import org.apache.shiro.crypto.hash.Sha512Hash
import org.codehaus.groovy.grails.validation.MinSizeConstraint;
import org.springframework.dao.DataIntegrityViolationException

class UserController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		[users: User.list(params)]
	}

	def create() {
		[user: new User(params)]
	}

	def save() {
		def userInstance = new User(params)

		//		if user id is null we create a new user and have to generate a password + hash
		if(!userInstance.id){
			def passwordSalt = new SecureRandomNumberGenerator().nextBytes().getBytes()
			userInstance.passwordSalt = passwordSalt
			userInstance.passwordHash = new Sha512Hash(params.password,passwordSalt,1024).toBase64()
		}

		if (!userInstance.save(flush: true)) {
			render(view: "create", model: [user: userInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
		//        redirect(action: "show", id: userInstance.id)
		redirect(action: "index")
	}

	//    def show(Long id) {
	//        def userInstance = User.get(id)
	//        if (!userInstance) {
	//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
	//            redirect(action: "index")
	//            return
	//        }
	//
	//        [userInstance: userInstance]
	//    }

	def edit(Long id) {
		def userInstance = User.get(id)
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
			redirect(action: "index")
			return
		}

		[user: userInstance, password: new PasswordCommand()]
	}

	def update(Long id, Long version) {
		def userInstance = User.get(id)
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
			redirect(action: "index")
			return
		}

		if (version != null) {
			if (userInstance.version > version) {
				userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[message(code: 'user.label', default: 'User')] as Object[],
						"Another user has updated this User while you were editing")
				render(view: "edit", model: [userInstance: userInstance])
				return
			}
		}

		//        userInstance.properties = params
		bindData(userInstance, params, [include: ['username', 'roles']])

		if (!userInstance.save(flush: true)) {
			render(view: "edit", model: [user: userInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
		redirect(action: "edit", id: userInstance.id)
	}

	def delete(Long id) {
		def userInstance = User.get(id)
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
			redirect(action: "index")
			return
		}

		try {
			userInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
			redirect(action: "index")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
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
			password2 = password ? true: ['pix.user.password.dont_match']
		}
	}
}
