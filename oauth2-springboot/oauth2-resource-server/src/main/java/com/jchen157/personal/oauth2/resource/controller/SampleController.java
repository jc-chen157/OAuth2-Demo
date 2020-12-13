package com.jchen157.personal.oauth2.resource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scope")
public class SampleController {
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/all")
    public Object getAll() {
        return "you have the all scope to see everything";
    }

    //    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping(value = "/read")
    public Object getRead() {
        return "you only have READ scope";
    }
}
