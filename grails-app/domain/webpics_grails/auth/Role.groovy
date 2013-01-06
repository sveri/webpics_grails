package webpics_grails.auth

import webpics_grails.pic.Album

class Role {
    String name

    static hasMany = [ users: User, permissions: String, albums:  Album ]
    static belongsTo = User

    static mapping = {sort: "name"}

    static constraints = {
	name(nullable: false, blank: false, unique: true)
    }

    String toString(){
	return name
    }
}
