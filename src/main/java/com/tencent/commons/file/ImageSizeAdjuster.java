package com.tencent.commons.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author bobzbfeng
 */
public class ImageSizeAdjuster {

    public static final int MOBILE_IMAGE_WIDTH = 480;
    private static Logger logger = LoggerFactory.getLogger(ImageSizeAdjuster.class);

    private byte[] imageData;

    public ImageSizeAdjuster() {
    }

    public ImageSizeAdjuster(byte[] imageData) {
        this.imageData = imageData;
    }

    public ImageSizeAdjuster imageData(byte[] imageData) {
        this.imageData = imageData;
        return this;
    }

    public byte[] adjustForMobile(int width) throws IOException {
        if (width <= 0) {
            width = MOBILE_IMAGE_WIDTH;
        }
        return adjust(width, ImageUtils.DEFAULT_IMAGE_TYPE);
    }

    public byte[] adjust(int width, String extension) throws IOException {
        if (width <= 0) {
            logger.debug("Expect width[{}] is less than 0, ignore adjust.", width);
            return imageData;
        }

        final BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageData));
        final int imageHeight = image.getHeight();
        final int imageWidth = image.getWidth();

        if (imageWidth != width) {
            logger.debug("The image width is {}, larger than mobile max width {}, will adjust it", imageWidth, width);
            int newHeight = newHeight(imageHeight, imageWidth, width);
            imageData = ImageUtils.reduceAsBytes(image, width, newHeight, extension);
        }

        return imageData;
    }

    private int newHeight(int imageHeight, int imageWidth, int width) {
        final BigDecimal newHeight = (new BigDecimal(width).multiply(new BigDecimal(imageHeight)))
                .divide(new BigDecimal(imageWidth), 2, BigDecimal.ROUND_HALF_UP);
        return newHeight.intValue();
    }
}