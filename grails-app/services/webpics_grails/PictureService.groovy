package webpics_grails

import java.nio.file.Files
import java.util.zip.ZipEntry
import java.util.zip.ZipFile

import webpics_grails.pic.Album
import webpics_grails.pic.Photo

import java.util.zip.ZipOutputStream

class PictureService {

    def grailsApplication

    def pictureServiceJava

    def compressAlbum(Album album) {
        def photos = Photo.findAllByAlbum(album)

        File baseTempImageFile = Files.createTempFile(album.id.toString(), "pix").toFile();
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(baseTempImageFile));

        for (photo in photos) {
            FileInputStream fis = new FileInputStream(getFilePath(album.id.toString(), photo.name, "big"));

            out.putNextEntry(new ZipEntry(photo.name));

            byte[] b = new byte[1024];

            int count;

            while ((count = fis.read(b)) > 0) {
                out.write(b, 0, count);
            }
            fis.close();
        }
        out.close()
        return baseTempImageFile.absolutePath
    }

    def storeZippedImages(ZipFile zipFile, String albumId) throws Exception {
        Enumeration<ZipEntry> e = zipFile.entries()

        while (e.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) e.nextElement()
            if (entry.isDirectory()) {
                continue;
            }
            def fileName = entry.getName()
            fileName = fileName.substring(fileName.lastIndexOf('/') + 1)
            storePicture(zipFile.getInputStream(entry), albumId, fileName)
        }
    }

    def storePicture(InputStream is, String albumId, String fileName) throws Exception {

        def albumBasePath = getAlbumBasePath(albumId)

        pictureServiceJava.createImageDirsIfNotExist(albumBasePath)

        File baseTempImageFile = pictureServiceJava.saveInputStreamToTempFile(is, fileName)

        pictureServiceJava.resizeAndSaveImages(baseTempImageFile, albumBasePath, fileName)
        storePhotoInDb(fileName, albumId)
    }

    def storePhotoInDb(String fileName, String albumId) {
        new Photo(album: Album.get(albumId), name: fileName).save()
    }

    def getAlbumBasePath(String albumId) {
        return grailsApplication.config.pix.image_base_path + File.separator + albumId
    }

    def getFilePath(String albumId, String fileName, String size) {
        def retVal = grailsApplication.config.pix.image_base_path + File.separator
        retVal += albumId ? albumId + File.separator : ""
        retVal += size ? size + File.separator : ""
        retVal += fileName ? fileName : ""
        return retVal
    }
}
