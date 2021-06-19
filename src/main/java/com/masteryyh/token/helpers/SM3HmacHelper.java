package com.masteryyh.token.helpers;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

import java.nio.charset.StandardCharsets;

/**
 * SM3 HMac helper
 * Based on BouncyCastle HMac and SM3Digest
 */
public class SM3HmacHelper {
    /**
     * Generate HMAC string from plain text using specific key string
     * @param key HMAC key
     * @param plaintext Plain text
     * @return HMAC string encoded in URLBase64
     */
    public static String getMac(String key, String plaintext) {
        HMac hmac = new HMac(new SM3Digest());
        KeyParameter parameter = new KeyParameter(key.getBytes(StandardCharsets.UTF_8));

        byte[] plainRaw = plaintext.getBytes(StandardCharsets.UTF_8);
        hmac.init(parameter);
        hmac.update(plainRaw, 0, plainRaw.length);

        byte[] result = new byte[hmac.getMacSize()];
        hmac.doFinal(result, 0);

        return Base64Helper.encode(result);
    }

    /**
     * Verify HMAC string by calculate again and compare with it
     * @param key HMAC key
     * @param plaintext Original plain text
     * @param mac HMAC string
     * @return Verification result
     */
    public static boolean verifyMac(String key, String plaintext, String mac) {
        return getMac(key, plaintext).equals(mac);
    }
}
