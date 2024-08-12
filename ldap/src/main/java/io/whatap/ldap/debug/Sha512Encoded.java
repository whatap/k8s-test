package io.whatap.ldap.debug;

import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Sha512Encoded {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = io.whatap.ldap.encoder.Sha512Encoded.passwordEncoder();
        String encodedPassword = passwordEncoder.encode("qwer1234");
        System.out.println(encodedPassword);
    }
}
