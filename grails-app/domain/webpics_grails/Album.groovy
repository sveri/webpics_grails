package webpics_grails

class Album {
	static hasMany = [photo:Photo]
	
	String name;

    static constraints = {
    }
}
