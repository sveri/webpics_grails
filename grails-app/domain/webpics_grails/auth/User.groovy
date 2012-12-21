package webpics_grails.auth

class User {
    String username
    String passwordHash
	byte[] passwordSalt
    
    static hasMany = [ roles: Role, permissions: String ]

    static constraints = {
        username(nullable: false, blank: false, unique: true)
    }
}
