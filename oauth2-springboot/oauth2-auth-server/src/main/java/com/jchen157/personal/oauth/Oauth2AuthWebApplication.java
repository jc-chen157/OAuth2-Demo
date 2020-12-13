package com.jchen157.personal.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@SpringBootApplication
public class Oauth2AuthWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2AuthWebApplication.class, args);
    }
}
