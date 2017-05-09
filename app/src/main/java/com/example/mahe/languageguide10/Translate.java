package com.example.mahe.languageguide10;

import java.net.URL;
import java.net.URLEncoder;




public final class Translate extends YandexTranslatorAPI {

    public static final String SERVICE_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?";
    public static final String TRANSLATION_LABEL = "text";

    //prevent instantiation
    public Translate() {
    }

    public static String execute(final String text, final Language from, final Language to) throws Exception {
        validateServiceState(text);
        final String params =
                PARAM_API_KEY + URLEncoder.encode(apiKey, ENCODING)
                        + PARAM_LANG_PAIR + URLEncoder.encode(from.toString(), ENCODING) + URLEncoder.encode("-", ENCODING) + URLEncoder.encode(to.toString(), ENCODING)
                        + PARAM_TEXT + URLEncoder.encode(text, ENCODING);
        final URL url = new URL(SERVICE_URL + params);
        return retrievePropArrString(url, TRANSLATION_LABEL).trim();

    }

    public static void validateServiceState(final String text) throws Exception {
        final int byteLength = text.getBytes(ENCODING).length;
        if (byteLength > 10240) { // TODO What is the maximum text length allowable for Yandex?
            throw new RuntimeException("TEXT_TOO_LARGE");
        }
        validateServiceState();
    }

    public static void main(String[] args) {
        // Translate obj1=new Translate();
        //start("Hello there. How are you?");
      /*  try {
            Translate.setKey(ApiKeys.YANDEX_API_KEY);
            String translation = Translate.execute("The quick brown fox jumps over the lazy dog.", Language.ENGLISH, Language.SPANISH);
            System.out.println("Translation: " + translation);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/
    }
    public static String start(String str, Language from, Language to)
    {

        String translation = "working..";
        try {
            Translate.setKey("trnsl.1.1.20170407T101727Z.5b8fec3d825fbe59.332c1a1d7815539398d5fa4e7b725f30e502482e");
            
            //System.out.println(Translate.execute(str, Language.ENGLISH, Language.FRENCH));

           translation = Translate.execute(str, from, to);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return translation;
    }
}