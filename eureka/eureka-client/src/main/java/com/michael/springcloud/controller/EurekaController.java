package com.michael.springcloud.controller;

import com.michael.springcloud.pojo.PortInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EurekaController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/sayhello")
    public String sayHello(){
        return "my port of this eureka is "+port;
    }

    @PostMapping("/getport")
    public PortInfo sayPortInfo(@RequestBody PortInfo portInfo){
        log.info("Your name is "+portInfo.getName());
        portInfo.setPort(port);
        return portInfo;
    }
}
