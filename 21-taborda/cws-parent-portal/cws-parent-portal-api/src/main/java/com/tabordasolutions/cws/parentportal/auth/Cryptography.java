package com.tabordasolutions.cws.parentportal.auth;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class Cryptography {
    private String key;
    private Cipher encryptCipher;
    private Cipher decryptCipher;

    public Cryptography(String key) {
        this.key = key;
    }

    public String encrypt(String phrase){
        byte[] encrypted = "".getBytes();
        Cipher encryptCipher = getEncryptCipher();
        try {
            assert encryptCipher != null;
            encrypted = encryptCipher.doFinal(phrase.getBytes());
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return new String( Base64.encodeBase64(encrypted));
    }

    public String decrypt(String encodePhrase){
        String phrase = "";

        Cipher decryptCipher= getDecryptCipher();
        try {
            byte[] decordedValue = Base64.decodeBase64(encodePhrase);
            phrase = new String(decryptCipher.doFinal(decordedValue));
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return phrase;
    }

    private Cipher getEncryptCipher(){
        return encryptCipher != null ? encryptCipher : buildCipher(Cipher.ENCRYPT_MODE);
    }
    private Cipher getDecryptCipher(){
        return decryptCipher != null ? decryptCipher : buildCipher(Cipher.DECRYPT_MODE);
    }

    private Cipher buildCipher(int encryptMode) {
        Cipher cipher;
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            try {
                cipher = Cipher.getInstance("AES");
                cipher.init(encryptMode, aesKey);
            } catch (NoSuchAlgorithmException e) {
                cipher = null;
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
                cipher = null;
            } catch (InvalidKeyException e) {
                e.printStackTrace();
                cipher = null;
            }
        return cipher;
    }
}
