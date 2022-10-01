package com.biswajit.testlock.controller;

import com.biswajit.testlock.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MainController {

    private final AppService appService;

    @GetMapping("/test")
    public void test() throws InterruptedException {
        appService.init();
    }
}
