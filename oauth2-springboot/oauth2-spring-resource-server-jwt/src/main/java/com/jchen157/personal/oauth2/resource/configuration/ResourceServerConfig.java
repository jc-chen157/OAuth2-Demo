package com.jchen157.personal.oauth2.resource.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

  private Logger logger = LoggerFactory.getLogger(ResourceServerConfig.class);

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http
            .authorizeRequests((authorizeRequests) ->
                    authorizeRequests
                            .antMatchers(HttpMethod.GET, "/scope/all").hasAuthority("SCOPE_all")
                            .antMatchers(HttpMethod.GET, "/scope/read").hasAuthority("SCOPE_read")
                            .anyRequest().authenticated()
            )
            .oauth2ResourceServer().jwt();
    // @formatter:on
  }
}
