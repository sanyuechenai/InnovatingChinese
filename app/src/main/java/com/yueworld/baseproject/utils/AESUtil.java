/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yueworld.baseproject.utils;


import android.util.Base64;

import com.yueworld.baseproject.utils.StringUtils;

import java.security.GeneralSecurityException;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * An Obfuscator that uses AES to encrypt data.
 */
public class AESUtil {
    private static final String UTF8 = "UTF-8";
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";
    private static final byte[] IV =
            {(byte) 200, (byte) 221, (byte) 162, 7, 34, (byte) 135, 22, (byte) 190, 99, (byte) 179, (byte) 186, (byte) 167, 40, (byte) 220, 82, (byte) 255};

//    private Cipher mEncryptor;
//    private Cipher mDecryptor;

//    /**
//     * @param salt an array of random bytes to use for each (un)obfuscation
//     */
//    public AESUtil(byte[] salt) {
//        try {
//            SecretKey secret = new SecretKeySpec(salt, CIPHER_ALGORITHM);
//            mEncryptor = Cipher.getInstance(CIPHER_ALGORITHM);
//            mEncryptor.init(Cipher.ENCRYPT_MODE, secret, new IvParameterSpec(IV));
//            mDecryptor = Cipher.getInstance(CIPHER_ALGORITHM);
//            mDecryptor.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(IV));
//        } catch (GeneralSecurityException e) {
//            // This can't happen on a compatible Android device.
//            throw new RuntimeException("Invalid environment", e);
//        }
//    }

    public static String encrypt(byte[] salt,String original) {
        if (original == null) return null;
        String encrypt_result;
        try {
            // Header is appended as an integrity check
            SecretKey secret = new SecretKeySpec(salt, CIPHER_ALGORITHM);
            Cipher mEncryptor = Cipher.getInstance(CIPHER_ALGORITHM);
            mEncryptor.init(Cipher.ENCRYPT_MODE, secret, new IvParameterSpec(IV));
            byte[] enc_origin = mEncryptor.doFinal(original.getBytes(UTF8));
            // Used  android.util.Base64(flag:NO_WRAP)
            encrypt_result = StringUtils.isEmpty(enc_origin) ? "" : Base64.encodeToString(enc_origin, Base64.NO_WRAP);
            return encrypt_result;
        }catch (Exception e){
            throw new RuntimeException("Invalid environment", e);
        }
    }

    public static String decrypt(byte[] salt,String obfuscated){
        if (obfuscated == null) return null;
        String result ;
        try {
            SecretKey secret = new SecretKeySpec(salt, CIPHER_ALGORITHM);
            Cipher mDecryptor = Cipher.getInstance(CIPHER_ALGORITHM);
            mDecryptor.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(IV));
            byte[] dec_input =   Base64.decode(obfuscated, Base64.NO_WRAP);
            byte[] dec_result = (null == dec_input || dec_input.length <= 0) ? null : mDecryptor.doFinal(dec_input);
            result = StringUtils.isEmpty(dec_result) ? "" : new String(dec_result, UTF8);
            return result;
        }catch (Exception e){
            throw new RuntimeException("Invalid environment", e);
        }
    }

    public static String encrypt(Map<String,String> map){
        return encrypt(Constant.secretKey.getBytes(),GsonHelp.objectToJsonString(map));
    }
}
