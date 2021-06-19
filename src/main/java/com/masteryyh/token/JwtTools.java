package com.masteryyh.token;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.masteryyh.token.helpers.SM3HmacHelper;
import com.masteryyh.token.jwt.Jwt;
import com.masteryyh.token.jwt.JwtHeader;
import com.masteryyh.token.jwt.JwtPayload;
import org.bouncycastle.util.encoders.UrlBase64;

import java.util.HashMap;

/**
 * JWT related tools ( Just for this project :-) )
 */
@SuppressWarnings("unchecked")
public class JwtTools {
    /**
     * Parse JWT string to Jwt object
     * @param rawJwt JWT string
     * @return Jwt object
     * @throws Exception Invalid JWT string format, or Jackson processing exceptions
     */
    public static Jwt parseJwt(String rawJwt) throws Exception {
        String[] jwtParts = rawJwt.split("\\.");

        if (jwtParts.length == 3) {
            ObjectMapper mapper = new ObjectMapper();
            JwtHeader header = mapper.readValue(new String(UrlBase64.decode(jwtParts[0])), JwtHeader.class);

            HashMap<String, String> payloadData = mapper.readValue(new String(UrlBase64.decode(jwtParts[1])), HashMap.class);
            JwtPayload payload = new JwtPayload(payloadData);

            return new Jwt(header, payload, jwtParts[2]);
        }
        throw new Exception("Invalid JWT format.");
    }

    /**
     * Verify a JWT's signature
     * @param rawJwt JWT string
     * @param key Key used to sign
     * @return Verification result
     * @throws Exception Invalid JWT string format, or Jackson processing exceptions
     */
    public static boolean verifyJwt(String rawJwt, String key) throws Exception {
        String[] jwtParts = rawJwt.split("\\.");

        if (jwtParts.length == 3) {
            String header = new String(UrlBase64.decode(jwtParts[0]));
            String payload = new String(UrlBase64.decode(jwtParts[1]));

            return SM3HmacHelper.verifyMac(key, header + "." + payload, jwtParts[2]);
        }
        throw new Exception("Invalid JWT format.");
    }
}
