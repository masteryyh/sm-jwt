package com.masteryyh.token.helpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SM3HmacHelperTest {
    @Test
    void getMac() {
        String plaintext = "SM3 HMac helper test";
        String key = "testkey123";

        assertEquals("Th7v-bVmamZ5dWFV38zm5y0L6_1uMG0s-4ESj6oPbGI.", SM3HmacHelper.getMac(key, plaintext));
    }

    @Test
    void verifyMac() {
        String plaintext = "SM3 HMac helper test";
        String key = "testkey123";
        String mac = "Th7v-bVmamZ5dWFV38zm5y0L6_1uMG0s-4ESj6oPbGI.";

        assertTrue(SM3HmacHelper.verifyMac(key, plaintext, mac));
    }
}