package com.bookstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

//    private final FileUploadDemo fileUploadDemo;

    @GetMapping
    public String testController(){
        return "test";
    }
//    @PostMapping
//    public String fileUpload(MultipartFile file, String fileName) {
//        return fileUploadDemo.fileUpload(file, fileName);
//    }

}
