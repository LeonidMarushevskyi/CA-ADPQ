package com.tabordasolutions.cws.parentportal.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CryptographyTest {
    private Cryptography crypt;
    private String key = "Bar12345Bar12345";

    @Before
    public void setup(){
       crypt = new Cryptography(key);
    }

    @Test
    public void testEncryptPhrases(){
        String phrase = "my secret";
        String encodePhrase = crypt.encrypt(phrase);
        assertFalse("Expect encoded Phrase not to be the same as the phrase", encodePhrase == phrase);
    }

    @Test
    public void testEncryptLongPhrases(){
        String phrase = "In a hole in the ground there lived a hobbit. Not a nasty, dirty, wet hole, filled with the ends of worms and an oozy smell, nor yet a dry, bare, sandy hole with nothing in it to sit down on or to eat: it was a hobbit-hole, and that means comfort.";
        String encodePhrase = crypt.encrypt(phrase);
        assertFalse("Expect encoded Phrase not to be the same as the phrase", encodePhrase == phrase);
    }

    @Test
    public void testEmptyPhrases(){
        String phrase = "";
        String encodePhrase = crypt.encrypt(phrase);
        assertFalse("Expect encoded Phrase not to be the same as the phrase", encodePhrase == phrase);
    }

    @Test
    public void testDecodePhrase(){
        String phrase = "my secret";
        String encodePhrase = crypt.encrypt(phrase);
        String decodedPhrase = crypt.decrypt(encodePhrase);
        assertTrue("Expected encoded and decoded phrases to be different", decodedPhrase != encodePhrase);
        assertEquals("Expected decoded phrase to equal original phrase", phrase, decodedPhrase);
    }
}
