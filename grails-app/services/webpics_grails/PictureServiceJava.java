package webpics_grails;

import static org.imgscalr.Scalr.OP_ANTIALIAS;
import static org.imgscalr.Scalr.OP_BRIGHTER;
import static org.imgscalr.Scalr.resize;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr.Method;

public class PictureServiceJava {

    private int sizeBig = 1920;
    private int sizeNormal = 700;
    private int sizeThumbs = 60;

    public void setSizes(int sizeThumbs, int sizeNormal, int sizeBig) {
        try {
            this.sizeThumbs = sizeThumbs;
            this.sizeNormal = sizeNormal;
            this.sizeBig = sizeBig;
        } catch (Exception e) {
        }
    }

    public File saveInputStreamToTempFile(final InputStream is, final String fileName, boolean keepOriginal, String albumBasePath) throws Exception {
        File baseTempImageFile = null;
        if (keepOriginal) {
            baseTempImageFile = getOrigFile(albumBasePath, fileName);
        } else {
            baseTempImageFile = Files.createTempFile(fileName, "pix").toFile();
        }

        final OutputStream out = new FileOutputStream(baseTempImageFile.getAbsolutePath());
        final byte[] buf = new byte[1024];
        int len;

        while ((len = is.read(buf)) > 0) {
            out.write(buf, 0, len);
        }

        out.close();
        is.close();

        return baseTempImageFile;
    }

    public void createImageDirsIfNotExist(final String albumBasePath) throws IOException {

        final Path pathTargetFolderNormal = Paths.get(albumBasePath);
        final Path pathTargetFolderBig = Paths.get(pathTargetFolderNormal + File.separator + "big");
        final Path pathTargetFolderThumb = Paths.get(pathTargetFolderNormal + File.separator + "thumbs");
        final Path pathTargetFolderOrig = Paths.get(pathTargetFolderNormal + File.separator + "orig");

        if (!Files.exists(pathTargetFolderNormal)) {
            Files.createDirectories(pathTargetFolderNormal);
            Files.createDirectories(pathTargetFolderBig);
            Files.createDirectories(pathTargetFolderThumb);
            Files.createDirectories(pathTargetFolderOrig);
        }

    }

    public void resizeAndSaveImages(final File baseTempImageFile, final String albumBasePath, final String fileName)
            throws IOException {

        final String fileExtension = com.google.common.io.Files.getFileExtension(fileName);

        final BufferedImage imageOriginal = ImageIO.read(baseTempImageFile);
        final BufferedImage thumbnail = createThumbnail(imageOriginal);
        final BufferedImage normal = createNormalImage(imageOriginal);
        final BufferedImage imageBig = createBig(imageOriginal);

        ImageIO.write(imageBig, fileExtension, getBigFile(albumBasePath, fileName));
        ImageIO.write(thumbnail, fileExtension, getThumbFile(albumBasePath, fileName));
        ImageIO.write(normal, fileExtension, getBaseFile(albumBasePath, fileName));
    }

    private File getBigFile(final String albumBasePath, final String fileName) {
        return new File(albumBasePath + File.separator + "big" + File.separatorChar + fileName);
    }

    private File getThumbFile(final String albumBasePath, final String fileName) {
        return new File(albumBasePath + File.separator + "thumbs" + File.separatorChar + fileName);
    }
    private File getOrigFile(final String albumBasePath, final String fileName) {
        return new File(albumBasePath + File.separator + "orig" + File.separatorChar + fileName);
    }

    private File getBaseFile(final String albumBasePath, final String fileName) {
        return new File(albumBasePath + File.separatorChar + fileName);
    }

    private BufferedImage createBig(final BufferedImage img) {
        // Create quickly, then smooth and brighten it.
        return resize(img, Method.SPEED, sizeBig, OP_ANTIALIAS, OP_BRIGHTER);
    }

    private BufferedImage createNormalImage(final BufferedImage img) {
        // Create quickly, then smooth and brighten it.
        return resize(img, Method.SPEED, sizeNormal, OP_ANTIALIAS, OP_BRIGHTER);
    }

    private BufferedImage createThumbnail(final BufferedImage img) {
        // Create quickly, then smooth and brighten it.
        return resize(img, Method.SPEED, sizeThumbs, OP_ANTIALIAS, OP_BRIGHTER);
    }


}
