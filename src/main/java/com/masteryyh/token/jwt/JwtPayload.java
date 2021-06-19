package com.masteryyh.token.jwt;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT Payload
 */
public class JwtPayload {
    private final Map<String, String> data;

    public JwtPayload(Map<String, String> data) {
        this.data = data;
    }

    public JwtPayload(long ttl) {
        data = new HashMap<>();

        long expire = Instant.now().toEpochMilli() + ttl;
        data.put("expire", String.valueOf(expire));
    }

    public JwtPayload() {
        data = new HashMap<>();
    }

    /**
     * Add a value to the payload if nothing has been associated with this name (key)
     * @param name Data name (key)
     * @param value Value
     * @return Succeeded or not
     */
    public boolean addValueIfAbsent(String name, String value) {
        return data.putIfAbsent(name, value) == null;
    }

    /**
     * Add a value to the payload
     * @param name Data name (key)
     * @param value Value
     */
    public void addValue(String name, String value) {
        data.put(name, value);
    }

    /**
     * Remove a value from the payload
     * @param name Data name (key)
     * @return Succeeded or not
     */
    public boolean removeKey(String name) {
        return data.remove(name) != null;
    }

    public Map<String, String> getData() {
        return data;
    }
}
