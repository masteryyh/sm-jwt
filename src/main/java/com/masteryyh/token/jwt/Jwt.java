package com.masteryyh.token.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masteryyh.token.helpers.Base64Helper;
import com.masteryyh.token.helpers.SM3HmacHelper;
import java.util.StringJoiner;

/**
 * JWT Structure
 */
public class Jwt {
    private JwtHeader header;
    private JwtPayload payload;
    private String signature;

    public Jwt(JwtHeader header, JwtPayload payload, String signature) {
        this.header = header;
        this.payload = payload;
        this.signature = signature;
    }

    public Jwt(JwtHeader header, JwtPayload payload) {
        this.header = header;
        this.payload = payload;
    }

    public Jwt() {
    }

    public JwtHeader getHeader() {
        return header;
    }

    public void setHeader(JwtHeader header) {
        this.header = header;
    }

    public JwtPayload getPayload() {
        return payload;
    }

    public void setPayload(JwtPayload payload) {
        this.payload = payload;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * Update JWT signature part
     * @param key Key used to sign data
     * @throws JsonProcessingException Jackson exception
     */
    public void updateSignature(String key) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String header = mapper.writeValueAsString(this.header);
        String payload = mapper.writeValueAsString(this.payload.getData());

        this.signature = SM3HmacHelper.getMac(key, header + "." + payload);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Jwt.class.getSimpleName() + "[", "]")
                .add("header=" + header)
                .add("payload=" + payload)
                .add("signature='" + signature + "'")
                .toString();
    }

    /**
     * Generate JWT string
     * @param key Key used to sign data
     * @return JWT string
     * @throws JsonProcessingException Jackson exception
     */
    public String toJwtString(String key) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String header = mapper.writeValueAsString(this.header);
        String payload = mapper.writeValueAsString(this.payload.getData());

        this.signature = SM3HmacHelper.getMac(key, header + "." + payload);

        String headerPart = Base64Helper.encode(header);
        String payloadPart = Base64Helper.encode(payload);
        return headerPart + "." + payloadPart + "." + this.signature;
    }
}
