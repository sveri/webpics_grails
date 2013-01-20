package webpics_grails

import org.apache.commons.logging.LogFactory
import webpics_grails.pic.Album
import webpics_grails.pic.Photo

import java.util.zip.ZipFile

import com.google.common.io.Files


class AlbumController {

    private static final log = LogFactory.getLog(this)

    def albumService

    static allowedMethods = [save: "POST", upload: ["POST", "GET"],
	album: "GET", jsupload: "POST", zipupload: "POST", getFile: "GET"]

    def pictureService
    def userService

    def album() {
        if (!userService.isUserAllowedToSeeAlbum(params.id)){
	    redirect(controller: "auth", action: 'unauthorized')
	    return
        }
        def album = Album.get(params.id)
        [album: album, photos: Photo.findAllByAlbum(album)]
    }

    def upload() {
	[album: Album.get(params.id)]
    }

    def index() {
        def albums
        try{
            albums = userService.listAllAlbumsUserIsAllowedToSee()
        }catch(e){
            albums = []
	    log.error(e)
        }
        [albums: albums, albumForm: new Album()]
    }

    def save() {
        def albumInstance = new Album(params)
        if (!albumInstance.save(flush: true)) {
            render(view: "index", model: [albums: Album.findAll(sort: "name"), albumForm: albumInstance])
	    log.error("action: save - error while trying to save an album")
            return
        }

	try{
	    albumService.addAlbumToLoggedInUserRoles(albumInstance)
        }catch(e){
    	    log.error(e)
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'album.label', default: 'Album'), albumInstance.name])
        redirect(action: "index", params: params)
    }

    // receive the files from js
    def jsupload() {
        try {
            pictureService.storePicture(request.getInputStream(), params.albumid, params.qqfile)
        } catch (e) {
	    log.error(e)
            render(status: response.SC_INTERNAL_SERVER_ERROR, text: "{success: false}")
            return
        }
        render(status: response.SC_OK, text: "{success: true}")
    }

    def zipupload() {
        def zipFile = new ZipFile(params.file_path?.trim())
        try {
            pictureService.storeZippedImages(zipFile, params.albumid)
        } catch (e) {
	    log.error(e)
            flash.message = message(code: 'pix.something_went_wrong')
            redirect(action: "upload", params: [id: params.albumid])
            return
        }
        flash.message = message(code: 'pix.album.album.upload.zip_succeeded')
        redirect(action: "upload", params: [id: params.albumid])
    }

    def getFile() {
        def photo = Photo.get(params.photoid)

	if(!userService.isUserAllowedToSeeAlbum(photo.album.id.toString())){
	    log.error("action: getFile() - something went wrong while trying to get an image")
	    redirect(controller: "auth", action: 'unauthorized')
	    return
	}

        def file = new File(pictureService.getFilePath(photo.album.id.toString(), photo.name, params.size))
        def img = file.bytes
	def fileExtension = Files.getFileExtension(photo.name)

        if(fileExtension ==~ "(?i)jpg"){
	    fileExtension = "jpeg"
        }

	response.setHeader("Content-Type", 'image/' + fileExtension)
        response.outputStream << img
        response.outputStream.flush()
    }

    def downloadAlbum() {
	def album = Album.get(params.id)
	def file
	try{
	    file = new File(pictureService.compressAlbum(album))
        }catch(e){
       	    log.error(e)
        }

	response.setHeader("Content-disposition", "attachment;filename=${album.id}.zip")
        response.contentType = 'application/zip'
        response.outputStream << file.bytes
        response.outputStream.flush()
    }

    def rotateImage(){
        def photo = Photo.get(params.photoId)
        photo.rotVal = params.int('rotVal')
//        pictureService.rotateImage(photo.album.id.toString(), photo.name, params.rotVal)
        render ''
    }
}
