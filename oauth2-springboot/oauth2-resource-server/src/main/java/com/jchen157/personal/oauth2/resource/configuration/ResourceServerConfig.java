package com.jchen157.personal.oauth2.resource.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  private Logger logger = LoggerFactory.getLogger(ResourceServerConfig.class);

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers("/scope/all")
            .access("#oauth2.hasScope('all')")
            .antMatchers("/scope/read")
            .access("#oauth2.hasScope('read')")
            .anyRequest()
            .authenticated();
  }
}
