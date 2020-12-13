package com.jchen157.personal.oauth.configuration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {

  final PasswordEncoder passwordEncoder;

  final UserDetailsService customUserDetailService;

  final AuthenticationManager authenticationManager;

  final TokenStore jwtToken;
  final JwtAccessTokenConverter jwtAccessTokenConverter;

  public OAuth2Configuration(
          PasswordEncoder passwordEncoder,
          UserDetailsService customUserDetailService,
          AuthenticationManager authenticationManager,
          TokenStore jwtToken,
          JwtAccessTokenConverter jwtAccessTokenConverter) {
    this.passwordEncoder = passwordEncoder;
    this.customUserDetailService = customUserDetailService;
    this.authenticationManager = authenticationManager;
    this.jwtToken = jwtToken;
    this.jwtAccessTokenConverter = jwtAccessTokenConverter;
  }

  @Override
  public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
            .authenticationManager(authenticationManager)
            .userDetailsService(customUserDetailService)
            .tokenStore(jwtToken)
            .accessTokenConverter(jwtAccessTokenConverter);
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients
            .inMemory()
            // client id and secret info
            .withClient("user-client")
            .secret(passwordEncoder.encode("user-secret-8888"))
            // default authority granted when using client_credentials
            .authorities("ADMIN")
            // offer different ways to get a token, for example ,if choosing password, then
            .authorizedGrantTypes(
                    "client_credentials", "refresh_token", "authorization_code", "password")
            .scopes("all", "read", "write")
            .accessTokenValiditySeconds(3600)
            .redirectUris("http://localhost:8090/login");
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.allowFormAuthenticationForClients();
    security.checkTokenAccess("isAuthenticated()");
    security.tokenKeyAccess("isAuthenticated()");
  }
}
