package com.jchen157.personal.oauth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Order(2)
@Configuration
class UserDetailServiceConfiguration extends WebSecurityConfigurerAdapter {
  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    return new InMemoryUserDetailsManager(
            User.withDefaultPasswordEncoder()
                    .username("admin")
                    .password("123456")
                    .roles("ADMIN")
                    .build());
  }
  //  @Override
  //  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
  //    clients
  //            .inMemory()
  //            .withClient("user-client")
  //            .secret(passwordEncoder().encode("user-secret-8888"))
  //            .scopes("all", "read")
  //            .authorizedGrantTypes(
  //                    "authorization_code", "password", "client_credentials", "implicit",
  // "refresh_token")
  //            .redirectUris("http://localhost:8090/login");
  //  }
  //
  //  @Bean
  //  public PasswordEncoder passwordEncoder() {
  //    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  //  }
  //
  //  @Bean
  //  @Override
  //  public UserDetailsService userDetailsService() {
  //    return new InMemoryUserDetailsManager(
  //            User.withDefaultPasswordEncoder()
  //                    .username("admin")
  //                    .password("123456")
  //                    .roles("ADMIN")
  //                    .build());
  //  }
  //
  //  @Override
  //  @Bean
  //  public AuthenticationManager authenticationManagerBean() throws Exception {
  //    return super.authenticationManagerBean();
  //  }
}
