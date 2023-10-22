package net.manyahl.employeeservice.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/test")
public class TestController {
    @GetMapping(produces = "application/xml")
    public @ResponseBody Book getBook(){
        return new Book(1,"The Count of Monte Cristo");
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
