
package webpics_grails.service



import grails.test.mixin.*
import org.junit.*

import webpics_grails.PictureService;
import webpics_grails.pic.Album;

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(PictureService)
@Mock([Album])
class PictureServiceTests {

    @Test
    void testEmptyGetFilePath() {
	assert service.getFilePath("", "", "") == grailsApplication.config.pix.image_base_path + File.separator
    }

    void testyGetFilePathWithAlbumId() {
	assert service.getFilePath("5", "", "") == grailsApplication.config.pix.image_base_path + File.separator + "5" + File.separator
    }

    void testyGetFilePathWithAlbumIdAndFileNameAndSizeNormal() {
	assert service.getFilePath("5", "fileName.txt", "") == grailsApplication.config.pix.image_base_path + File.separator + "5" + File.separator + "fileName.txt"
    }

    void testyGetFilePathWithAlbumIdAndFileNameAndSizeBig() {
	assert service.getFilePath("5", "fileName.txt", "big") == grailsApplication.config.pix.image_base_path + File.separator + "5" + File.separator + "big" + File.separator + "fileName.txt"
    }

    void testyGetFilePathWithAlbumIdAndFileNameAndSizeThumbs() {
	assert service.getFilePath("5", "fileName.txt", "thumbs") == grailsApplication.config.pix.image_base_path + File.separator + "5" + File.separator + "thumbs" + File.separator + "fileName.txt"
    }
}
