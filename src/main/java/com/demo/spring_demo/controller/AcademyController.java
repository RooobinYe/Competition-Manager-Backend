package com.demo.spring_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.spring_demo.model.Academy;

@RestController
@RequestMapping("/api/academy")
public class AcademyController {
    
    @GetMapping
    public Academy getAcademy() {
        // 这里只是示例，实际应该从服务层获取数据
        Academy academy = new Academy();
        return academy;
    }
} 