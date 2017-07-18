package by.epam.litvin.util;

import by.epam.litvin.constant.GeneralConstant;

import java.util.Base64;

public class StringEncoder {
    public static String encode(String string) {
        String encodedString;
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] hash = encoder.encode((GeneralConstant.SALT + string).getBytes());
        encodedString = new String(encoder.encode(string.getBytes()));
        return encodedString + GeneralConstant.SALT;

    }
}
