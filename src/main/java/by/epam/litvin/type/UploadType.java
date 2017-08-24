package by.epam.litvin.type;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import static by.epam.litvin.constant.GeneralConstant.UPLOAD_PROPERTIES;

public enum UploadType {

    NEWS, AVATARS;

    private static final Logger LOGGER = LogManager.getLogger();
    private static ResourceBundle jspBundle = ResourceBundle.getBundle(UPLOAD_PROPERTIES);

    public String getUploadFolder() {
        try {
            return jspBundle.getString(this.toString());

        } catch (MissingResourceException e) {
            LOGGER.log(Level.ERROR, "Path to upload folder not found.", e);
            throw new RuntimeException("Path to upload folder not found.", e);

        } catch (ClassCastException e) {
            LOGGER.log(Level.ERROR, "Path to upload folder is not a string.", e);
            throw new RuntimeException("Path to upload folder is not a string.", e);
        }
    }
}
