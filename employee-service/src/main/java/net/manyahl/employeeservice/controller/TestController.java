package net.manyahl.employeeservice.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/api/test")
public class TestController {
    @Value("${test.message}")
    private String message;
    @GetMapping
    public Book getBook(){
        return new Book(1,message);
    }
}
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class Book{
    private int ISBN;
    private String title;
}
