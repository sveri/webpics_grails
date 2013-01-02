package webpics_grails

import webpics_grails.pic.Album;


class AlbumController {

	static allowedMethods = [save: "POST", upload: "GET", index: "GET", album: "GET", jsupload: "POST"]
//			static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def pictureService

	def album(){
		[album: Album.get(params.id)]
	}

	def upload(){
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
	def jsupload(){
            try{
                pictureService.storePicture(request.getInputStream(), params.albumid, params.qqfile)
            } catch (all) {
                render(status: response.SC_INTERNAL_SERVER_ERROR, text:"{success: false}")
            }
            render(status: response.SC_OK, text:"{success: true}")
            return
	}

//		def show(Long id) {
//			def albumInstance = Album.get(id)
//			if (!albumInstance) {
//				flash.message = message(code: 'default.not.found.message', args: [message(code: 'album.label', default: 'Album'), id])
//				redirect(action: "list")
//				return
//			}
//
//			[albumInstance: albumInstance]
//		}
//
//		def edit(Long id) {
//			def albumInstance = Album.get(id)
//			if (!albumInstance) {
//				flash.message = message(code: 'default.not.found.message', args: [message(code: 'album.label', default: 'Album'), id])
//				redirect(action: "list")
//				return
//			}
//
//			[albumInstance: albumInstance]
//		}
//
//		def update(Long id, Long version) {
//			def albumInstance = Album.get(id)
//			if (!albumInstance) {
//				flash.message = message(code: 'default.not.found.message', args: [message(code: 'album.label', default: 'Album'), id])
//				redirect(action: "list")
//				return
//			}
//
//			if (version != null) {
//				if (albumInstance.version > version) {
//					albumInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
//							  [message(code: 'album.label', default: 'Album')] as Object[],
//							  "Another user has updated this Album while you were editing")
//					render(view: "edit", model: [albumInstance: albumInstance])
//					return
//				}
//			}
//
//			albumInstance.properties = params
//
//			if (!albumInstance.save(flush: true)) {
//				render(view: "edit", model: [albumInstance: albumInstance])
//				return
//			}
//
//			flash.message = message(code: 'default.updated.message', args: [message(code: 'album.label', default: 'Album'), albumInstance.id])
//			redirect(action: "show", id: albumInstance.id)
//		}
//
//		def delete(Long id) {
//			def albumInstance = Album.get(id)
//			if (!albumInstance) {
//				flash.message = message(code: 'default.not.found.message', args: [message(code: 'album.label', default: 'Album'), id])
//				redirect(action: "list")
//				return
//			}
//
//			try {
//				albumInstance.delete(flush: true)
//				flash.message = message(code: 'default.deleted.message', args: [message(code: 'album.label', default: 'Album'), id])
//				redirect(action: "list")
//			}
//			catch (DataIntegrityViolationException e) {
//				flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'album.label', default: 'Album'), id])
//				redirect(action: "show", id: id)
//			}
//		}
}
