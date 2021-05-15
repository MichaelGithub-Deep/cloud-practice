package com.michael.springcloud.controller;

import com.michael.springcloud.pojo.PortInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient client;

    @GetMapping("/hello")
    public String hello(){
        ServiceInstance instance = client.choose("eureka-client");
        if(instance == null){
            return "No Available Instance";
        }

        String target = String.format("http://%s:%s/sayhello", instance.getHost(), instance.getPort());
        log.info("url is {}", target);

        return restTemplate.getForObject(target,String.class);
    }

    @PostMapping("/getport")
    public PortInfo getPort(){
        ServiceInstance instance = client.choose("eureka-client");
        if(instance==null){
            return null;
        }
        String target = String.format("http://%s:%s/getport",instance.getHost(),instance.getPort());
        log.info("url is {}",target);
        PortInfo portInfo = new PortInfo();
        portInfo.setName("michael");
        portInfo.setPort("8888");
        return restTemplate.postForObject(target,portInfo, PortInfo.class);
    }
}
