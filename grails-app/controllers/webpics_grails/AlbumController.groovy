package webpics_grails

import webpics_grails.pic.Album
import webpics_grails.pic.Photo;

import java.util.zip.ZipFile;

import com.google.common.io.Files;


class AlbumController {

    static allowedMethods = [save: "POST", upload: "GET", index: "GET", album: "GET", jsupload: "POST", zipupload: "POST", getFile: "GET"]

    def pictureService

    def album() {
	def album = Album.get(params.id)
        [album: album, photos: Photo.findAllByAlbum(album)]
    }

    def upload() {
        [album: Album.get(params.id)]
    }

    def index() {
        def map = [albums: Album.listOrderByName(), albumForm: new Album()]
        render(view: "index", model: map)
    }

    def save() {
        def albumInstance = new Album(params)
        if (!albumInstance.save(flush: true)) {
            render(view: "index", model: [albums: Album.list(params), albumForm: albumInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'album.label', default: 'Album'), albumInstance.name])
        redirect(action: "index", params: params)
    }

    // receive the files from js
    def jsupload() {
        try {
            pictureService.storePicture(request.getInputStream(), params.albumid, params.qqfile)
        } catch (all) {
            render(status: response.SC_INTERNAL_SERVER_ERROR, text: "{success: false}")
        }
        render(status: response.SC_OK, text: "{success: true}")
        return
    }

    def zipupload() {
        def zipFile = new ZipFile(params.file_path?.trim())
        try {
            pictureService.storeZippedImages(zipFile, params.albumid)
        }  catch (all){
            flash.message = message(code: 'pix.something_went_wrong')
	    redirect(action: "upload", params: [id: params.albumid])
	    return
        }
        flash.message = message(code: 'pix.album.album.upload.zip_succeeded')
        redirect(action: "upload", params: [id: params.albumid])
    }

    def getFile(){
    	def photo = Photo.get(params.photoid)
	def file = new File(grailsApplication.config.pix.image_base_path + File.separator + photo.album.id
	    + File.separator + params.size + File.separator + photo.name )
	def img = file.bytes

	response.contentType = 'image/' + Files.getFileExtension(photo.name) // or the appropriate image content type
	response.outputStream << img
	response.outputStream.flush()
    }

}
