package webpics_grails.auth

class User {
    String username
    String passwordHash
    String email
    Boolean receivesUpdates
    byte[] passwordSalt

    static hasMany = [roles: Role, permissions: String]

    static constraints = {
        username(nullable: false, blank: false, unique: true)
        email(nullable: true, blank: true, unique: true)
        receivesUpdates(nullable: false)
    }

    String toString() {
        return username
    }
}
