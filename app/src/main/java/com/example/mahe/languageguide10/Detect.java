package com.example.mahe.languageguide10;

import java.net.URL;
import java.net.URLEncoder;

import java.io.*;
import java.lang.*;

/**
 * Provides an interface to the Yandex Translator Detect service method
 */
public final class Detect extends YandexTranslatorAPI {
    public static final String SERVICE_URL = "https://translate.yandex.net/api/v1.5/tr.json/detect?";
    public static final String DETECTION_LABEL = "lang";

    // prevent instantiation
    public Detect(){}

    /**
     * Detects the language of a supplied String.
     *
     * @param text The String to detect the language of.
     * @return A String containing the language
     * @throws Exception on error.
     */
    public static Language execute(final String text) throws Exception {
        validateServiceState(text);
        final String params =
                PARAM_API_KEY + URLEncoder.encode(apiKey,ENCODING)
                        + PARAM_TEXT + URLEncoder.encode(text,ENCODING);
        final URL url = new URL(SERVICE_URL + params);
        return Language.fromString(retrievePropString(url, DETECTION_LABEL));
    }

    public static void validateServiceState(final String text) throws Exception {
        final int byteLength = text.getBytes(ENCODING).length;
        if(byteLength>10240) { // TODO What is the maximum text length allowable for Yandex?
            throw new RuntimeException("TEXT_TOO_LARGE - Yandex Translator (Detect) can handle up to 10,240 bytes per request");
        }
        validateServiceState();
    }

    public static void main(String[] args) {
        try {
            Translate.setKey("trnsl.1.1.20170407T101727Z.5b8fec3d825fbe59.332c1a1d7815539398d5fa4e7b725f30e502482e");
            Language translation = Detect.execute("The quick brown fox jumps over the lazy dog.");
            System.out.println("Detected: " + translation.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
