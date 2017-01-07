package com.epam.training.lawAndSocial.utils;

public final class CheckUtils {

    public static boolean isNumeric(String value) {
        boolean isNumeric = true;
        if (value != null) {
            int size = value.length();

            for (int i = 0; i < size; i++) {
                if (!Character.isDigit(value.charAt(i))) {
                    isNumeric = false;
                }
            }
        }
        return isNumeric;
    }

}
