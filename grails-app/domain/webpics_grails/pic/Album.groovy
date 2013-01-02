package webpics_grails.pic

class Album {
    static hasMany = [photo:Photo]

    String name

    static constraints = { name unique: true }

    String toString(){
	return name
    }
}
