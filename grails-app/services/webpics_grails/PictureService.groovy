package webpics_grails

import java.util.zip.ZipFile

import webpics_grails.pic.Album
import webpics_grails.pic.Photo

class PictureService {

    def grailsApplication

    def pictureServiceJava

    def storeZippedImages(ZipFile zipFile, String albumId) throws Exception{
	zipFile.entries().each {
	    storePicture(zipFile.getInputStream(it), albumId, it.name)
	}
    }

    def storePicture(InputStream is, String albumId, String fileName) throws Exception {

	def albumBasePath = grailsApplication.config.pix.image_base_path + File.separator + albumId

	pictureServiceJava.createImageDirsIfNotExist(albumBasePath)

	File baseTempImageFile = pictureServiceJava.saveInputStreamToTempFile(is, fileName)

	pictureServiceJava.resizeAndSaveImages(baseTempImageFile, albumBasePath, fileName)
	storePhotoInDb(fileName, albumId)
    }

    def storePhotoInDb(String fileName, String albumId) {
	new Photo(album: Album.get(albumId), name: fileName).save()
    }
}
