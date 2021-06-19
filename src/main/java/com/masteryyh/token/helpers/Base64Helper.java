package com.masteryyh.token.helpers;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Helper {
    public static String encode(String plaintext) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(plaintext.getBytes(StandardCharsets.UTF_8));
    }

    public static String encode(byte[] plainRaw) {
        return Base64.getUrlEncoder().encodeToString(plainRaw);
    }

    public static String decode(String base64) {
        return new String(Base64.getUrlDecoder().decode(base64));
    }
}
