package com.hao.springboottest.utils;

import com.sun.crypto.provider.PBKDF2HmacSHA1Factory;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;

public class PasswordStore {
    private static Integer result_len = 128;
    private static Integer salt_len = 24;
    private static Integer iterations = 1000;
//    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
//        String password = "lzh666";
//        String temp = pbkdf2(generateSalt(),password);
//        System.out.println(temp+ "   is   " + temp.length());
//        temp = pbkdf2(generateSalt(),password);
//        System.out.println(temp+ "   is   " + temp.length());
//        temp = pbkdf2(generateSalt(),password);
//        System.out.println(temp+ "   is   " + temp.length());
//
//    }
    public static String[] exchange(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] pasAndSalt = new String[2];
        pasAndSalt[0] = generateSalt();
        pasAndSalt[1] = pbkdf2(pasAndSalt[0],password);
        return pasAndSalt;
    }

    public static String pbkdf2(String salt,String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(),fromHex(salt),iterations,result_len);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return toHex(f.generateSecret(spec).getEncoded());
    }

    public static boolean authenticate(String attemptedPassword, String encryptedPassword, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 用相同的盐值对用户输入的密码进行加密
        String encryptedAttemptedPassword = pbkdf2(salt, attemptedPassword);
        // 把加密后的密文和原密文进行比较，相同则验证成功，否则失败
        return encryptedAttemptedPassword.equals(encryptedPassword);
    }




    /**
     * @describe: 通过加密的强随机数生成盐(最后转换为16进制)
     */
    public static String generateSalt() throws NoSuchAlgorithmException {
        Random random1 = new Random();
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[random1.nextInt(salt_len) + 1];
        random.nextBytes(salt);
        return toHex(salt);
    }





    /**

     * @describe: 十六进制字符串转二进制字符串

     */
    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }
    /**
     * @describe: 二进制字符串转十六进制字符串

     */
    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }




}
