package org.example.springbootdemo.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@RestController
public class webFluxController {

    @Resource(name = "virtualThreadScheduler")
    private Scheduler virtualThreadScheduler;

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.fromCallable(() -> {
            // 模拟长时间操作
            Thread.sleep(300);
            System.out.println(Thread.currentThread().toString());
            return "Hello, Virtual Threads!";
        }).subscribeOn(virtualThreadScheduler); // 使用虚拟线程调度器
    }

    @GetMapping("/hello2")
    public String hello2() throws InterruptedException {
        Thread.sleep(300);
        System.out.println(Thread.currentThread().toString());
        return "Hello, Platform Threads!";
    }
}
