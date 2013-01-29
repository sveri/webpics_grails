package webpics_grails.auth

import webpics_grails.pic.Album

class Role {
    String name

    static ADMINISTRATOR = 'Administrator'

    static hasMany = [ users: User, permissions: String, albums:  Album ]
    static belongsTo = User

    static mapping = {sort: "name"
        albums cascade: "save-update"
    }

    static constraints = {
	name(nullable: false, blank: false, unique: true)
    }

    String toString(){
	return name
    }
}
