package com.jchen157.personal.oauth2.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@EnableResourceServer
@SpringBootApplication
public class Oauth2ResourceWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2ResourceWebApplication.class, args);
    }
}
