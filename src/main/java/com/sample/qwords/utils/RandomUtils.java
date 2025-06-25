package com.sample.qwords.utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;

import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * This class provides utility methods for random operations.
 * 
 * Note: This code was copied from the internet and needs to be refactored.
 * The plan is to add AWS functionality to this application in the future.
 */
// import java.security.SecureRandom;
// SecureRandom is used to generate a secure random key for the cipher

public class RandomUtils {
    static Cipher cipher;

    private static final String CIPHER_ALGORITHM = "AES";
    private static final int KEY_SIZE = 256;

    public static AWSCredentials getCredentials(String accessKey, String secretKey) {
        AWSCredentials creds = getCreds(accessKey, secretKey);
        return creds;
    }

    static AWSCredentials getCreds(String id, String key) {
        return new BasicAWSCredentials(id, key);
    }

    public static Cipher getCipher() {
        Cipher cipher = null;
        try {
            SecureRandom secureRandom = new SecureRandom();
            KeyGenerator keyGen = KeyGenerator.getInstance(CIPHER_ALGORITHM);
            keyGen.init(KEY_SIZE, secureRandom);
            SecretKey secretKey = keyGen.generateKey();
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
            // or handle the exception in an appropriate way
        }
        return cipher;
    }
}