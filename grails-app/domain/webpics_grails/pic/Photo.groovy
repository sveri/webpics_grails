package webpics_grails.pic

class Photo {

    static belongsTo = [album: Album]

    String name
    Album album
    int rotVal = 0

    static constraints = {
        name blank: false, null: false, unique: ['album']
        rotVal range: -361..361
    }

    void setRotVal(int val) {
        if (val < -360) {
            rotVal = val + 360
        }else if (val > 360) {
            rotVal = val - 360
        } else {
            rotVal = val
        }
    }

    String toString(){
	return name
    }
}
