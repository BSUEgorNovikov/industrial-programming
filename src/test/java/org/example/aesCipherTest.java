package org.example;

import static org.junit.jupiter.api.Assertions.*;

class aesCipherTest {

    @org.junit.jupiter.api.Test
    void encrypt() {
        String secretKey = "11111";

        String testStr1 = "4 + 3 * 4";
        String shouldEquals1 = "ekH/NsWHGdOif/HsRR2pBg==";
        String encTestStr1 = aesCipher.encrypt(testStr1, secretKey);

        assertEquals(shouldEquals1, encTestStr1);

        String testStr2 = "3 + 2 - 7";
        String shouldEquals2 = "2UT8QU2LMbRGQOl+nyp8ww==";
        String encTestStr2 = aesCipher.encrypt(testStr2, secretKey);

        assertEquals(shouldEquals2, encTestStr2);

        String testStr3 = "4 * 5 - 6 / 3";
        String shouldEquals3 = "Pk0HNNBbZ8/XTiC/aELQyg==";
        String encTestStr3 = aesCipher.encrypt(testStr3, secretKey);

        assertEquals(shouldEquals3, encTestStr3);
    }

    @org.junit.jupiter.api.Test
    void decrypt() {
        String secretKey = "11111";

        String shouldEquals1 = "4 + 3 * 4";
        String testStr1 = "ekH/NsWHGdOif/HsRR2pBg==";
        String encTestStr1 = aesCipher.decrypt(testStr1, secretKey);

        assertEquals(shouldEquals1, encTestStr1);

        String shouldEquals2 = "3 + 2 - 7";
        String testStr2 = "2UT8QU2LMbRGQOl+nyp8ww==";
        String encTestStr2 = aesCipher.decrypt(testStr2, secretKey);

        assertEquals(shouldEquals2, encTestStr2);

        String shouldEquals3 = "4 * 5 - 6 / 3";
        String testStr3 = "Pk0HNNBbZ8/XTiC/aELQyg==";
        String encTestStr3 = aesCipher.decrypt(testStr3, secretKey);

        assertEquals(shouldEquals3, encTestStr3);
    }
}