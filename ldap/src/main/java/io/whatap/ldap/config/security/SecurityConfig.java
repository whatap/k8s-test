package io.whatap.ldap.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //bind 방식은 확장성을 위해 나중에라도 연동할 수 있도록...
    //BindAuthenticator bindAuthenticator = new BindAuthenticator(contextSource);
    //bindAuthenticator.setUserDnPatterns(new String[]{"uid={0},ou=employee"});

    // 여기서 인증
    @Bean
    public MyLdapAuthenticator myLdapAuthenticator(){
        DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource("ldap://localhost:8389/dc=springframework,dc=org");
        contextSource.afterPropertiesSet();

        MyLdapAuthenticator myLdapAuthenticator = new MyLdapAuthenticator(contextSource);
        myLdapAuthenticator.setUserDnPatterns(new String[]{"uid={0},ou=employee"});
        return myLdapAuthenticator;
    }

    //populator 에서 security context 에 권한 부여
    @Bean
    public LdapAuthoritiesPopulator myLdapAuthoritiesPopulator(){
        return new MyLdapAuthoritiesPopulator();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        LdapAuthenticationProvider ldapAuthenticationProvider =
                new LdapAuthenticationProvider(myLdapAuthenticator(), myLdapAuthoritiesPopulator());

        auth.authenticationProvider(ldapAuthenticationProvider);
    }
}