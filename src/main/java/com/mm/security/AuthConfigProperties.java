package com.mm.security;

import java.util.Base64;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "auth")
@Setter
public class AuthConfigProperties {

    public String publicKey;
    public String privateKey;
    public String clientId;
    public String clientSecret;
    public int accessTokenValidity;
    public int refreshTokenValidity;

    public void setPublicKey(String publicKey) {
        this.publicKey = new String(Base64.getDecoder().decode(publicKey));
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = new String(Base64.getDecoder().decode(privateKey));
    }
}
