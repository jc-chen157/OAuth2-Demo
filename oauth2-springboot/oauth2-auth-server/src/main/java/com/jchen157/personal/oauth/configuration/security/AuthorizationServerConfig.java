package com.jchen157.personal.oauth.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UserDetailsService userDetailsService;

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

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    endpoints.authenticationManager(authenticationManager);
    endpoints.userDetailsService(userDetailsService);
  }

  /**
   * this endpoint will be used * by resource server to verify token. By default, any request to
   * `/oauth/check_token` will be denied,
   *
   * @param security
   * @see org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerSecurityConfiguration
   * for more detail.
   */
  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) {
    security.checkTokenAccess("permitAll()");
  }
}
