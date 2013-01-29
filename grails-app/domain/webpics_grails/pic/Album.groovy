package webpics_grails.pic

import webpics_grails.auth.Role

class Album {
    String name

    static constraints = {
        name unique: true
    }

    static hasMany = [roles : Role]
    static belongsTo = Role

    static mapping = { sort "name" }

    String toString() {
        return name
    }
}
