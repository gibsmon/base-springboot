package com.poliymorf.dagaitem.util;

import java.util.Random;

public class RandomUtil {

    public static String generateRandom() {
        Random random = new Random();
        int length = random.nextInt(10) + 1;

        int min = (int) Math.pow(10, length - 1); // Minimum value for the specified length
        int max = (int) Math.pow(10, length) - 1; // Maximum value for the specified length
        int i = random.nextInt(max - min + 1) + min;
        return EncryptUtil.encrypt8Bit(String.valueOf(i));
    }

    public static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}
