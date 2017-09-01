package by.epam.totalizator.util;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringEncoder {
    public static String encode(String password) {
        byte[] digest;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(password.getBytes("UTF-8"));
            digest = md.digest();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Encoding algoritm not found", e);

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unsupported encoding exception", e);
        }
        return Hex.encodeHexString(digest);
    }
}
