package webpics_grails

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
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
            def folder = getKeepOriginals() ? "orig" : "big"
            FileInputStream fis = new FileInputStream(getFilePath(album.id.toString(), photo.name, folder));

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

        File baseTempImageFile = pictureServiceJava.saveInputStreamToTempFile(is, fileName, getKeepOriginals(), albumBasePath)

        pictureServiceJava.setSizes(grailsApplication.config.pix.size.thumbs.toInteger(), grailsApplication.config.pix.size.normal.toInteger(),
                grailsApplication.config.pix.size.big.toInteger())
        pictureServiceJava.resizeAndSaveImages(baseTempImageFile, albumBasePath, fileName)
        storePhotoInDb(fileName, albumId)
    }

    def storePhotoInDb(String fileName, String albumId) {
        new Photo(album: Album.get(albumId), name: fileName).save()
    }

    def getAlbumBasePath(String albumId) {
        return grailsApplication.config.pix.image_base_path + File.separator + albumId
    }

    def getKeepOriginals() {
        return grailsApplication.config.pix.keep_originals == "true" ? true : false
    }

    def getLargestFileOfOneImage(String albumId, String fileName){
        def filePath = "";
        if (getKeepOriginals()){
            filePath = getFilePath(albumId, fileName, "orig")
        }

        if (!getKeepOriginals() || ! Files.exists(Paths.get(filePath)))  {
            filePath = getFilePath(albumId, fileName, "big")
        }
        return new File(filePath)
    }

    def getFilePath(String albumId, String fileName, String size) {
        def filePath = grailsApplication.config.pix.image_base_path + File.separator
        filePath += albumId ? albumId + File.separator : ""
        filePath += size ? size + File.separator : ""
        filePath += fileName ? fileName : ""
        return filePath
    }

    def getFileExtension(String fileName){
        def fileExtension = com.google.common.io.Files.getFileExtension(fileName)
        if (fileExtension ==~ "(?i)jpg") {
            fileExtension = "jpeg"
        }

        return fileExtension
    }
}
