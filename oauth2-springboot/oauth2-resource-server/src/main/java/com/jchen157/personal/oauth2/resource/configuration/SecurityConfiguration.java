package com.jchen157.personal.oauth2.resource.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity(debug = true)
@Configuration
@Order(1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable();
    permitAll(http);
//    http.authorizeRequests()
//        .antMatchers(HttpMethod.GET, "/scope/**")
//        .hasAuthority("ADMIN")
//        .antMatchers(HttpMethod.POST, "/scope/**")
//        .hasAuthority("something")
//        .anyRequest()
//        .authenticated();
  }

  private void permitAll(HttpSecurity http) throws Exception {

    http.authorizeRequests().anyRequest().permitAll();
  }
}
