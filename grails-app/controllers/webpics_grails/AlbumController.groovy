package webpics_grails

class AlbumController {
	
    def index() { 
		[albums: Album.listOrderByName(), albumForm: new Album()]
	}
	
	def addAlbum(){
		def album = new Album()
		album.name = params.albumname
		if(!album.save()){
			
		}
		redirect(action: 'index')
	}
	
	def album() {
		[album: Album.get(params.id)]
	}
	
	def upload() {
		def albumId = params.id
		[album: Album.get(params.id)]
	}
}
