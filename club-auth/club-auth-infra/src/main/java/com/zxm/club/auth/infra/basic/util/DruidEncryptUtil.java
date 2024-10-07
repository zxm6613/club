package com.zxm.club.auth.infra.basic.util;

import com.alibaba.druid.filter.config.ConfigTools;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * druid 加解密 工具
 *
 * @author zxm
 * @date 2024/10/01
 */
public class DruidEncryptUtil {
    private static String publicKey;
    private static String privateKey;

    static {
        try {
            String[] keyPair = ConfigTools.genKeyPair(512);
//            for (int i = 0; i < keyPair.length; i++) {
//                System.out.println(keyPair[i]);
//            }
            privateKey = keyPair[0];
            System.out.println("privateKey: " + privateKey);
            publicKey = keyPair[1];
            System.out.println("publicKey: " + publicKey);
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO: 这里按理来说应该是公钥加密，私钥解密，这里明显反着来了，等有时间了学习一下druid加密，看看怎么回事

    //加密
    public static String encrypt(String plainText) throws Exception {
        String encrypt = ConfigTools.encrypt(privateKey, plainText);
        System.out.println("encrypt = " + encrypt);
        return encrypt;
    }

    //解密
    public static String decrypt(String encryptText) throws Exception {
        String decrypt = ConfigTools.decrypt(publicKey, encryptText);
        System.out.println("decrypt = " + decrypt);
        return decrypt;
    }

    public static void main(String[] args) throws Exception {
        String encrypt = encrypt("123456");
        System.out.println("encrypt = " + encrypt);
    }

}
