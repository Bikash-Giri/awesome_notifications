package me.carda.awesome_notifications.utils;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class StringUtils {

    public static Boolean isNullOrEmpty(String string){
        return isNullOrEmpty(string, true);
    }

    public static Boolean isNullOrEmpty(String string, boolean considerWhiteSpaceAsEmpty){
        return string == null || (considerWhiteSpaceAsEmpty ? string.trim().isEmpty() : string.isEmpty());
    }

    public static String getValueOrDefault(String value, String defaultValue){
        return isNullOrEmpty(value) ? defaultValue : value;
    }

    public static String digestString(String reference){

        try {
            reference = reference.replaceAll("\\W+", "");

            byte[] bytes = new byte[0];
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                bytes = reference.getBytes(StandardCharsets.UTF_8);
            }

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(bytes);

            final BigInteger bigInt = new BigInteger(1, md.digest());
            return String.format("%032x", bigInt);

        } catch (Exception ex) {
            //("MD5 Cryptography Not Supported");
            return reference;
        }
    }
}
