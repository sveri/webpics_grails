package webpics_grails.service

import grails.test.GrailsUnitTestCase
import grails.test.mixin.*
import org.junit.*

import webpics_grails.PictureService;

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(PictureService)
class PictureServiceTests {

    @Test
    void testEmptyGetFilePath() {
        assert service.getFilePath("", "", "") == grailsApplication.config.pix.image_base_path + File.separator
    }

    void testGetFilePathWithAlbumId() {
        assert service.getFilePath("5", "", "") == grailsApplication.config.pix.image_base_path + File.separator + "5" + File.separator
    }

    void testGetFilePathWithAlbumIdAndFileNameAndSizeNormal() {
        assert service.getFilePath("5", "fileName.txt", "") == grailsApplication.config.pix.image_base_path + File.separator + "5" + File.separator + "fileName.txt"
    }

    void testGetFilePathWithAlbumIdAndFileNameAndSizeBig() {
        assert service.getFilePath("5", "fileName.txt", "big") == grailsApplication.config.pix.image_base_path + File.separator + "5" + File.separator + "big" + File.separator + "fileName.txt"
    }

    void testGetFilePathWithAlbumIdAndFileNameAndSizeThumbs() {
        assert service.getFilePath("5", "fileName.txt", "thumbs") == grailsApplication.config.pix.image_base_path + File.separator + "5" + File.separator + "thumbs" + File.separator + "fileName.txt"
    }
}
