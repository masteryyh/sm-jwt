package com.masteryyh.token.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masteryyh.token.helpers.SM3HmacHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtTest {

    @Test
    void toJwtString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        JwtHeader header = new JwtHeader();
        JwtPayload payload = new JwtPayload(1000 * 3600);

        payload.addValue("name", "jwt-test");
        payload.addValue("id", "A001");

        String headerJson = mapper.writeValueAsString(header);
        String payloadJson = mapper.writeValueAsString(payload.getData());

        String key = "test key 123456";
        String signature = SM3HmacHelper.getMac(key, headerJson + "." + payloadJson);

        Jwt token = new Jwt(header, payload);
        token.updateSignature(key);

        assertEquals(token.getSignature(), signature);

        String tokenRaw = token.toJwtString(key);
        assertEquals(tokenRaw.split("\\.").length, 3);
    }
}