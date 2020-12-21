package com.jchen157.personal.oauth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
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

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
