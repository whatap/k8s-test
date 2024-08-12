package io.whatap.ldap.debug;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha512 {
    /*
    hello
    qwer1234
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String raw = "qwer1234";
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(raw.getBytes());
        String hex = String.format("%0128x", new BigInteger(1, md.digest()));
        System.out.println(hex);

    }
}
