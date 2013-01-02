package webpics_grails

import java.nio.file.Files

class PictureService {

    def grailsApplication

    def pictureServiceJava

    def storePicture(InputStream is, String albumId, String fileName) throws Exception{

	def albumBasePath = grailsApplication.config.pix.image_base_path + File.separator + albumId

	pictureServiceJava.createImageDirsIfNotExist(albumBasePath)

	File baseTempImageFile = pictureServiceJava.saveInputStreamToTempFile(is, fileName)

	pictureServiceJava.resizeAndSaveImages(baseTempImageFile, albumBasePath, fileName)
    }

    def storePhotoInDb(String fileName, String albumId){
    }
}
