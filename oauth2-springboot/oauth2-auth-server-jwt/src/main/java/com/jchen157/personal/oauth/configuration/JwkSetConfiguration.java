package com.jchen157.personal.oauth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerTokenServicesConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@Import(AuthorizationServerTokenServicesConfiguration.class)
public class JwkSetConfiguration extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private JwtAccessTokenConverter tokenConverter;
  @Autowired
  private TokenStore tokenStore;
  @Autowired
  AuthenticationManager authenticationManager;

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

  //
  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    // @formatter:off
    endpoints
            .authenticationManager(this.authenticationManager)
            .accessTokenConverter(tokenConverter)
            .tokenStore(tokenStore);
    // @formatter:on
  }
}
