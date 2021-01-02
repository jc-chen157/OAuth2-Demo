package com.jchen157.personal.oauth2.resource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scope")
public class SampleController {
    @GetMapping(value = "/all")
    public Object getAll() {
        return "you have the all scope to see everything";
    }

    @GetMapping(value = "/read")
    public Object getRead() {
        return "you only have READ scope";
    }
}
