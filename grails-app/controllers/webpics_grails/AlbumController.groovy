package webpics_grails

import webpics_grails.auth.User
import webpics_grails.pic.Album
import webpics_grails.pic.Photo

import java.util.zip.ZipEntry
import java.util.zip.ZipFile

import com.google.common.io.Files

import java.util.zip.ZipOutputStream


class AlbumController {

    static allowedMethods = [save: "POST", upload: "GET", upload: ["POST", "GET"],
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
//	if (request.xhr) {
//	    render template: "albumUpload", model: [album: Album.get(params.id)]
//	} else {
//	}
	[album: Album.get(params.id)]
    }

    def index() {
        [albums: userService.listAllAlbumsUserIsAllowedToSee(), albumForm: new Album()]
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
            return
        }
        render(status: response.SC_OK, text: "{success: true}")
    }

    def zipupload() {
        def zipFile = new ZipFile(params.file_path?.trim())
        try {
            pictureService.storeZippedImages(zipFile, params.albumid)
        } catch (all) {
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
        def tempFileName = pictureService.compressAlbum(album)
	def file = new File(tempFileName)

	response.setHeader("Content-disposition", "attachment;filename=${album.id}.zip")
        response.contentType = 'application/zip'
        response.outputStream << file.bytes
        response.outputStream.flush()
    }
}
