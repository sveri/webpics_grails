package webpics_grails.pic

class Photo {

    String name
    Album album

    static constraints = {
	name blank: false, null: false
    }
}
