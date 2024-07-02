package com.xworkz.crisis.util;

import java.security.SecureRandom;

public class PasswordUtil {

    private static final String ALPHABETIC_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC_CHARS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_+-=[]{}|;:,.<>?";
    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword(int length) {
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            String charSet = getRandomCharSet();
            int randomIndex = random.nextInt(charSet.length());
            char randomChar = charSet.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    private static String getRandomCharSet() {
        String[] charSets = {ALPHABETIC_CHARS, NUMERIC_CHARS,SPECIAL_CHARS};
        int randomIndex = random.nextInt(charSets.length);
        return charSets[randomIndex];
    }
}
