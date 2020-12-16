package com.jchen157.personal.oauth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.security.KeyPair;

@Import(AuthenticationConfiguration.class)
@Configuration
public class JwkSetConfiguration extends AuthorizationServerConfigurerAdapter {

  AuthenticationManager authenticationManager;
  KeyPair keyPair;

  public JwkSetConfiguration(
          AuthenticationConfiguration authenticationConfiguration, KeyPair keyPair) throws Exception {

    this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
    this.keyPair = keyPair;
  }

  // ... client configuration, etc.
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients
            .inMemory()
            .withClient("user-client")
            .secret(passwordEncoder().encode("user-secret-8888"))
            .scopes("all", "read")
            .authorizedGrantTypes(
                    "authorization_code", "password", "client_credentials", "implicit", "refresh_token")
            .redirectUris("http://localhost:8090/login");
  }

  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }


  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    // @formatter:off
    endpoints
            .authenticationManager(this.authenticationManager)
            .accessTokenConverter(accessTokenConverter())
            .tokenStore(tokenStore());
    // @formatter:on
  }

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(accessTokenConverter());
  }

  @Bean
  public JwtAccessTokenConverter accessTokenConverter() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setKeyPair(this.keyPair);
    return converter;
  }
}
