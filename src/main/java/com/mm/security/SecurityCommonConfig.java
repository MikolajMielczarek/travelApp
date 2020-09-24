package com.mm.security;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
public class SecurityCommonConfig {

    @Autowired
    private AuthConfigProperties properties;

    @Bean
    public TokenEnhancerChain enhancerChain() {
        TokenEnhancerChain chain = new TokenEnhancerChain();
        chain.setTokenEnhancers(Arrays.asList(tokenConverter()));
        return chain;
    }

    @Bean
    public JwtAccessTokenConverter tokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(properties.privateKey);
        converter.setVerifierKey(properties.publicKey);
        return converter;
    }
}
