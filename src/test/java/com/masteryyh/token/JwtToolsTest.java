package com.masteryyh.token;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masteryyh.token.helpers.Base64Helper;
import com.masteryyh.token.helpers.SM3HmacHelper;
import com.masteryyh.token.jwt.Jwt;
import com.masteryyh.token.jwt.JwtHeader;
import com.masteryyh.token.jwt.JwtPayload;
import org.bouncycastle.util.encoders.Base64;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class JwtToolsTest {

    @Test
    void parseJwt() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        JwtHeader header = new JwtHeader();
        JwtPayload payload = new JwtPayload(1000 * 3600);
        String key = "test key123456";

        payload.addValue("name", "lollol123");

        String headerJson = mapper.writeValueAsString(header);
        String payloadJson = mapper.writeValueAsString(payload.getData());

        String signature = SM3HmacHelper.getMac(key, headerJson + "." + payloadJson);

        Jwt jwt = new Jwt(header, payload);
        String token = jwt.toJwtString(key);

        Jwt parsed = JwtTools.parseJwt(token);
        assertEquals(parsed.getHeader(), header);
        assertEquals(parsed.getPayload(), payload);
        assertEquals(parsed.getSignature(), signature);
    }

    @Test
    void verifyJwt() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        JwtHeader header = new JwtHeader();
        JwtPayload payload = new JwtPayload(1000 * 3600);
        String key = "test key123456";

        payload.addValue("name", "lollol123");

        Jwt jwt = new Jwt(header, payload);
        String token = jwt.toJwtString(key);

        assertTrue(JwtTools.verifyJwt(token, key));

        token = token.substring(1);

        try {
            assertFalse(JwtTools.verifyJwt(token, key));
            System.out.println("Token invalid");
        } catch (Exception e) {
            System.out.println("Token format invalid");
            assertTrue(e.getMessage().contains("base64"));
        }
    }
}