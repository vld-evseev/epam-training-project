package com.epam.training.lawAndSocial.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public final class EncodingUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(EncodingUtils.class);

    public static String encodeBase64(URL url) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (url != null) {
            try (InputStream is = url.openStream()) {
                byte[] byteChunk = new byte[4096];
                int n;

                while ((n = is.read(byteChunk)) > 0) {
                    baos.write(byteChunk, 0, n);
                }

                baos.flush();
            } catch (IOException e) {
                LOGGER.error("error while converting resource to byte array: {}", e.getMessage());
                LOGGER.error("path to resource: {}", url);
            }

        } else {
            LOGGER.error("trying to process NULL resource");
        }

        final byte[] bytes = baos.toByteArray();
        return Base64.encodeBase64String(bytes);
    }

}
