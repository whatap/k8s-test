package io.whatap.ldap.config.security;

import io.whatap.ldap.encoder.Sha512PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.SpringSecurityLdapTemplate;
import org.springframework.security.ldap.authentication.AbstractLdapAuthenticator;

public class MyLdapAuthenticator extends AbstractLdapAuthenticator {
    private final static Logger logger = LoggerFactory.getLogger(MyLdapAuthenticator.class);
    public MyLdapAuthenticator(ContextSource contextSource) {
        super(contextSource);
    }

    //    private PasswordEncoder passwordEncoder = new Sha512PasswordEncoder();
    private PasswordEncoder passwordEncoder = new Sha512PasswordEncoder();
    private String passwordAttributeName = "userPassword";
    private boolean usePasswordAttrCompare = false;

    @Override
    public DirContextOperations authenticate(Authentication auth) {
        String username = auth.getName();
        String password = (String)auth.getCredentials();

        DirContextOperations user = null;
        SpringSecurityLdapTemplate ldapTemplate = new SpringSecurityLdapTemplate(getContextSource());
        for (String userDn : getUserDns(username)) {
            try {
                user = ldapTemplate.retrieveEntry(userDn, getUserAttributes());
            }
            catch (NameNotFoundException ignore) {
                logger.error("Failed to retrieve user with {}, e=", userDn, ignore);
            }
            if (user != null) {
                break;
            }
        }
        if (user == null) {
            logger.error("Failed to retrieve user with any user DNs {}", getUserDns(username));
        }
        if (user == null && getUserSearch() != null) {
            logger.trace("Searching for user using " + getUserSearch());
            user = getUserSearch().searchForUser(username);
            if (user == null) {
                logger.debug("Failed to find user using " + getUserSearch());
            }
        }
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        if (logger.isTraceEnabled()) {
            logger.trace("Comparing password attribute '{}' for user '{}'",
                    this.passwordAttributeName, user.getDn());
        }

        //현재는 항상 false
        if (this.usePasswordAttrCompare && isPasswordAttrCompare(user, password)) {
            logger.debug("Locally matched password attribute '{}' for user '{}'",
                    this.passwordAttributeName, user.getDn());
            return user;
        }
        if (isLdapPasswordCompare(user, ldapTemplate, password)) {
            logger.debug("LDAP-matched password attribute '{}' for user '{}'",
                    this.passwordAttributeName, user.getDn());
            //매치 성공 했을 때 attribute 를 살펴보자

            logger.info("user={}", user);
            return user;
        }
        throw new BadCredentialsException(
                this.messages.getMessage("PasswordComparisonAuthenticator.badCredentials", "Bad credentials"));
    }

    private boolean isPasswordAttrCompare(DirContextOperations user, String password) {
        String passwordAttrValue = getPassword(user);
        return this.passwordEncoder.matches(password, passwordAttrValue);
    }

    private String getPassword(DirContextOperations user) {
        Object passwordAttrValue = user.getObjectAttribute(this.passwordAttributeName);
        if (passwordAttrValue == null) {
            return null;
        }
        if (passwordAttrValue instanceof byte[]) {
            return new String((byte[]) passwordAttrValue);
        }
        return String.valueOf(passwordAttrValue);
    }

    private boolean isLdapPasswordCompare(DirContextOperations user, SpringSecurityLdapTemplate ldapTemplate,
                                          String password) {
        String encodedPassword = this.passwordEncoder.encode(password);
        byte[] passwordBytes = Utf8.encode(encodedPassword);
        return ldapTemplate.compare(user.getDn().toString(), this.passwordAttributeName, passwordBytes);
    }
}
