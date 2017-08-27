package by.epam.litvin.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Uploader {
    private static final Logger LOGGER = LogManager.getLogger();

    public boolean uploadImage(Part imagePart, File fullPath,
                               String imageExtension, int... cropParams) {

        if (cropParams == null || cropParams.length != 6 ||
                imagePart == null || fullPath == null ||
                imageExtension == null) {
            return false;
        }

        Formatter formatter = new Formatter();
        int pointX1 = cropParams[0];
        int pointX2 = cropParams[1];
        int pointY1 = cropParams[2];
        int pointY2 = cropParams[3];
        int height = cropParams[4];
        int width = cropParams[5];

        boolean isWrote = true;
        try {
            ImageIO.setUseCache(false);
            BufferedImage avatar = ImageIO.read(imagePart.getInputStream());
            avatar = formatter.formatImage(avatar, pointX1,
                    pointX2, pointY1, pointY2, height, width);

            if (avatar == null) {
                isWrote = false;
            }
            if (isWrote) {
                isWrote = ImageIO.write(avatar, imageExtension, fullPath);
            }

        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Upload image error", e);
            isWrote = false;
        }

        return isWrote;
    }
}
