package com.example.takehomedejamobile.modele;

import android.util.Base64;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESCipher {

    String keyPass;

    public AESCipher(String key) {
        this.keyPass = key;
    }

    public String encrypt(String data) throws Exception {
        SecretKeySpec key = generateKey(keyPass);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = cipher.doFinal(data.getBytes());
        String encryptedData = Base64.encodeToString(encVal, Base64.DEFAULT);
        return encryptedData;

    }

    public SecretKeySpec generateKey(String pass) throws Exception{
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = pass.getBytes("UTF-8");
        digest.update(bytes, 0,bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretkey = new SecretKeySpec(key, "AES");
        return secretkey;
    }
}
