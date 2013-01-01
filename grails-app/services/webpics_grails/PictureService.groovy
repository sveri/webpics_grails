package webpics_grails

import org.springframework.aop.ThrowsAdvice

class PictureService {

    def grailsApplication

    def pictureServiceJava

    def storePicture(InputStream is, String albumId, String fileName) throws Exception{

	def albumBasePath = grailsApplication.config.pix.image_base_path + File.separator + albumId

	pictureServiceJava.createImageDirsIfNotExist(albumBasePath)

	File baseImageFile = new File(albumBasePath + File.separator + fileName)
	saveInputStreamToFile(baseImageFile, is)

	pictureServiceJava.resizeAndSaveImages(baseImageFile, albumBasePath)
    }

    def saveInputStreamToFile(File baseImageFile, InputStream is) throws Exception{
	OutputStream out=new FileOutputStream (baseImageFile.getAbsolutePath())
	byte[] buf = new byte [1024]
	int len

	while((len=is.read(buf)) > 0) {
	    out.write(buf,0,len)
	}

	out.close()
	is.close()
    }
}
