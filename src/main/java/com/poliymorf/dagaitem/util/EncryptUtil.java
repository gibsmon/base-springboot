package com.poliymorf.dagaitem.util;

import com.poliymorf.dagaitem.data.constanta.GlobalMessage;
import com.poliymorf.dagaitem.util.exception.BusinessException;
import lombok.SneakyThrows;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class EncryptUtil {

    @SneakyThrows
    public static String encrypt8Bit(String plaintext)  {
        ZoneId jakartaZoneId = ZoneId.of("Asia/Jakarta");
        Date currentDate = Calendar.getInstance(TimeZone.getTimeZone(jakartaZoneId)).getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        byte[] desEncryptionKey = sdf.format(currentDate).getBytes(StandardCharsets.UTF_8);
        return desEcbEncryptToBase64(desEncryptionKey, plaintext);
    }

    public static String decrypt8Bit(String encryptText) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ZoneId jakartaZoneId = ZoneId.of("Asia/Jakarta");
        Date currentDate = Calendar.getInstance(TimeZone.getTimeZone(jakartaZoneId)).getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        byte[] desEncryptionKey = sdf.format(currentDate).getBytes(StandardCharsets.UTF_8);
        String decryptedtext = desEcbDecryptFromBase64(desEncryptionKey, encryptText);
        return decryptedtext;
    }

    private static byte[] generateRandomDesKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[8]; // 8 bytes for des, 16 bytes for TDES 2 and 24 bytes for TDES 3
        secureRandom.nextBytes(key);
        return key;
    }

    private static String desEcbEncryptToBase64(byte[] desKey, String data) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(desKey, "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        String ciphertextBase64 = base64Encoding(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        return ciphertextBase64;
    }

    private static String desEcbDecryptFromBase64(byte[] desKey, String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] encryptedData = base64Decoding(data);
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(desKey, "DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return new String(cipher.doFinal(encryptedData));
    }

    private static String des3EcbEncryptToBase64(byte[] des3Key, String data) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(des3Key, "DESede");
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        String ciphertextBase64 = base64Encoding(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        return ciphertextBase64;
    }

    private static String des3EcbDecryptFromBase64(byte[] des3Key, String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] encryptedData = base64Decoding(data);
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(des3Key, "DESede");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return new String(cipher.doFinal(encryptedData));
    }

    private static byte[] tdes2ToDes3Key(byte[] tdes2Key) {
        byte[] tdes3Key = new byte[24];
        System.arraycopy(tdes2Key, 0, tdes3Key, 0, 16);
        System.arraycopy(tdes2Key, 0, tdes3Key, 16, 8);
        return tdes3Key;
    }

    private static String base64Encoding(byte[] input) {
        return Base64.getEncoder().encodeToString(input);
    }

    private static byte[] base64Decoding(String input) {
        try{
            return Base64.getDecoder().decode(input);
        }catch (Exception e) {
           throw new BusinessException(GlobalMessage.UNAUTHORIZED); 
        }
    }


}
