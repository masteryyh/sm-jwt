package com.masteryyh.token.algorithms.hmac;

import org.bouncycastle.crypto.params.KeyParameter;

import java.nio.charset.StandardCharsets;

public class SHA512Algorithm {
    public final String name = "hs512";
    private KeyParameter key;

    public SHA512Algorithm(String key) {
        this.key = new KeyParameter(key.getBytes(StandardCharsets.UTF_8));
    }

    public String getKey() {
        return new String(this.key.getKey());
    }

    public void setKey(String key) {
        this.key = new KeyParameter(key.getBytes(StandardCharsets.UTF_8));
    }
}
