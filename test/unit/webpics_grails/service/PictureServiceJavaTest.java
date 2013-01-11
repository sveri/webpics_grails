package webpics_grails.service;


import com.google.common.io.Files;
import org.junit.Assert;
import org.junit.Test;

import webpics_grails.PictureServiceJava;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: sveri
 * Date: 09.01.13
 * Time: 19:46
 * To change this template use File | Settings | File Templates.
 */
public class PictureServiceJavaTest {

    private PictureServiceJava pictureService;

    @Test
    public void testCreateImageDirs() throws IOException {
        pictureService = new PictureServiceJava();
        File tempDir = Files.createTempDir();
        String albumBasePath = tempDir.getAbsolutePath().toString() + File.separator + "alksfdj";

        pictureService.createImageDirsIfNotExist(albumBasePath);
        Assert.assertTrue(new File(albumBasePath + File.separator + "thumbs").exists());
        Assert.assertTrue(new File(albumBasePath + File.separator + "big").exists());
    }

}
