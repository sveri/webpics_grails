package webpics_grails

import webpics_grails.pic.Album
import webpics_grails.pic.Photo

import java.nio.file.Files

class PictureService {

    def grailsApplication

    def pictureServiceJava

    def storeZippedImages(File zipFile ) throws Exception{
        zipFile.entries().each {
            println zipFile.getInputStream(it).text
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
