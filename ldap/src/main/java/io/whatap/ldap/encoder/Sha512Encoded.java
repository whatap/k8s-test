package io.whatap.ldap.encoder;

import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Sha512Encoded {
    public static PasswordEncoder passwordEncoder(){
        return new MessageDigestPasswordEncoder("SHA-512");
    }
}
