package com.epam.training.lawAndSocial.utils;

public final class ServletUtils {

    private ServletUtils() {
    }

    public static boolean paramExists(String param) {
        return param != null && !param.isEmpty();
    }

}
