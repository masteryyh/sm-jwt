package com.masteryyh.token.jwt;

/**
 * JWT Header
 */
public class JwtHeader {
    private String alg;
    private String type;

    public JwtHeader(String alg, String type) {
        this.alg = alg;
        this.type = type;
    }

    public JwtHeader() {
        this.alg = "hsm3";
        this.type = "jwt";
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
