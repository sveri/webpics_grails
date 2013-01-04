package webpics_grails

import java.util.zip.ZipEntry
import java.util.zip.ZipFile

import webpics_grails.pic.Album
import webpics_grails.pic.Photo

import java.util.zip.ZipInputStream

class PictureService {

    def grailsApplication

    def pictureServiceJava

    def storeZippedImages(ZipFile zipFile, String albumId) throws Exception {
        Enumeration<ZipEntry> e = zipFile.entries()

        while(e.hasMoreElements()){
            ZipEntry entry = (ZipEntry) e.nextElement()
            if(entry.isDirectory())
            {
                continue;
            }
            def fileName = entry.getName()
            fileName = fileName.substring(fileName.lastIndexOf('/') + 1)
            storePicture(zipFile.getInputStream(entry), albumId, fileName)
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
