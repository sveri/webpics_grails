package webpics_grails.pic

class Album {
//    static hasMany = [photo:Photo]

    String name

    static constraints = { name unique: true }

    static mapping = {
        sort "name"
    }

    String toString() {
        return name
    }
}
