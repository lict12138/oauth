package com.tencent.commons.file;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author bobzbfeng
 */
public abstract class ImageUtils {


    public static final java.util.List<String> IMAGE_EXTENSIONS = Arrays.asList("png", "jpg", "jpeg", "gif", "bmp");
    public static final String DEFAULT_IMAGE_TYPE = "jpeg";

    //Update load max image size: 1MB(1024*1024*1)
    public static final long UPLOAD_MAX_IMAGE_SIZE = 1024 * 1024l;


    //private
    private ImageUtils() {
    }


    /**
     * Check the file name is image file or not.
     *
     * @param filename File name
     * @return True is image, otherwise false
     */
    public static boolean isImageFilename(String filename) {
        if (StringUtils.isEmpty(filename)) {
            return false;
        }
        String extension = FilenameUtils.getExtension(filename);
        return IMAGE_EXTENSIONS.contains(extension.toLowerCase());
    }


    /**
     * Check the size is available image size or not.
     *
     * @param size size
     * @return True is available, otherwise false
     */
    public static boolean isAvailableUploadImageSize(long size) {
        return size <= UPLOAD_MAX_IMAGE_SIZE;
    }


    /**
     * Reduce image as bytes
     *
     * @param origImg    Original image
     * @param newWidth   reduce image width
     * @param newHeight  reduce image height
     * @param formatName Image filename extension:  png, gif, jpg ...
     * @return Reduce image data
     * @throws java.io.IOException IOException
     */
    public static byte[] reduceAsBytes(BufferedImage origImg, int newWidth, int newHeight, String formatName) throws IOException {
        final BufferedImage newImage = reduce(origImg, newWidth, newHeight, isPngOrGif(formatName));
        return imageAsBytes(newImage, formatName);
    }

    private static boolean isPngOrGif(String formatName) {
        return "png".equalsIgnoreCase(formatName) || "gif".equalsIgnoreCase(formatName);
    }

    private static byte[] imageAsBytes(BufferedImage newImage) throws IOException {
        return imageAsBytes(newImage, DEFAULT_IMAGE_TYPE);
    }

    private static byte[] imageAsBytes(BufferedImage newImage, String formatName) throws IOException {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(newImage, formatName, output);
        return output.toByteArray();
    }


    /**
     * Reduce image
     *
     * @param origImg   Original image
     * @param newWidth  reduce image width
     * @param newHeight reduce image height
     * @param pngOrGif  Is png or gif type image
     * @return Reduce image
     * @throws java.io.IOException IOException
     */
    public static BufferedImage reduce(BufferedImage origImg, int newWidth, int newHeight, boolean pngOrGif) throws IOException {
        final int origWidth = origImg.getWidth();
        final int origHeight = origImg.getHeight();

        if (origWidth == newWidth && origHeight == newHeight) {
            return origImg;
        }

        final Image scaledInstance = origImg.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = newImage.createGraphics();
        if (pngOrGif) {
            newImage = g2d.getDeviceConfiguration().createCompatibleImage(newWidth, newHeight, Transparency.TRANSLUCENT);
            g2d.dispose();
            g2d = newImage.createGraphics();
        }

        g2d.drawImage(scaledInstance, 0, 0, newWidth, newHeight, null);
        g2d.dispose();

        return newImage;
    }

    /**
     * Crop image
     *
     * @param origImg   Original image
     * @param newWidth  cut image newWidth
     * @param newHeight cut image newHeight
     * @param newX      cut image x (left top)
     * @param newY      cut image y (left top)
     * @param pngOrGif  Is png or gif type image
     * @return cut image bytes
     * @throws java.io.IOException IOException
     */
    public static byte[] crop(BufferedImage origImg, int newWidth, int newHeight, int newX, int newY, boolean pngOrGif) throws IOException {

        final int origWidth = origImg.getWidth();
        final int origHeight = origImg.getHeight();
        if (newX + newWidth > origWidth) {
            newWidth = origWidth;
        }
        if (newY + newHeight > origHeight) {
            newHeight = origHeight;
        }

        Image image = origImg.getScaledInstance(origWidth, origHeight, Image.SCALE_DEFAULT);
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = newImage.createGraphics();
        if (pngOrGif) {
            newImage = g2d.getDeviceConfiguration().createCompatibleImage(newWidth, newHeight, Transparency.TRANSLUCENT);
            g2d.dispose();
            g2d = newImage.createGraphics();
        }
        g2d.drawImage(image, 0, 0, newWidth, newHeight, newX, newY, newX + newWidth, newY + newHeight, null);
        g2d.dispose();

        return imageAsBytes(newImage);
    }
}
