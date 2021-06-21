package com.masteryyh.token.algorithms.hmac;

import org.bouncycastle.crypto.params.KeyParameter;

import java.nio.charset.StandardCharsets;

public class RIPEMD160Algorithm {
    public final String name = "hr160";
    private KeyParameter key;

    public RIPEMD160Algorithm(String key) {
        this.key = new KeyParameter(key.getBytes(StandardCharsets.UTF_8));
    }

    public String getKey() {
        return new String(this.key.getKey());
    }

    public void setKey(String key) {
        this.key = new KeyParameter(key.getBytes(StandardCharsets.UTF_8));
    }
}
