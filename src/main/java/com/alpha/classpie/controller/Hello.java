package com.alpha.classpie.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杨能
 * @create 2020/11/1
 */
@RestController
public class Hello {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello world";
    }
}
