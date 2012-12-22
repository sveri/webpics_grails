package webpics_grails

class AlbumController {
	
	List albums
	
    def index() { 
		albums = Album.listOrderByName()
	}
	
	def album() {
		[album: Album.get(params.id)]
	}
	
	def upload() {
		def albumId = params.id
		[album: Album.get(params.id)]
	}
}
