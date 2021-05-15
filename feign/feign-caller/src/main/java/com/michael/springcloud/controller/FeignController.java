package com.michael.springcloud.controller;

import com.michael.springcloud.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FeignController {

    @Autowired
    private IService service;

    @GetMapping("/sayhi")
    public String sayHi(){
        return service.sayhello();
    }
}
