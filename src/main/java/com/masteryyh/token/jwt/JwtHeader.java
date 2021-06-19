package com.masteryyh.token.jwt;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtHeader header = (JwtHeader) o;
        return Objects.equals(alg, header.alg) && Objects.equals(type, header.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alg, type);
    }
}
