package webpics_grails.pic

class Photo {

    static belongsTo = [album: Album]

    String name
    Album album

    static constraints = {
        name blank: false, null: false, unique: ['album']

    }
}
