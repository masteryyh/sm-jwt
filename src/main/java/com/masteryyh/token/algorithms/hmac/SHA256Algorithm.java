package com.masteryyh.token.algorithms.hmac;

import org.bouncycastle.crypto.params.KeyParameter;

import java.nio.charset.StandardCharsets;

public class SHA256Algorithm {
    public final String name = "hs256";
    private KeyParameter key;

    public SHA256Algorithm(String key) {
        this.key = new KeyParameter(key.getBytes(StandardCharsets.UTF_8));
    }

    public String getKey() {
        return new String(this.key.getKey());
    }

    public void setKey(String key) {
        this.key = new KeyParameter(key.getBytes(StandardCharsets.UTF_8));
    }
}
