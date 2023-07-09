package com.springproject.helloworldcicd;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SuppressWarnings("checkstyle:MissingJavadocType")
@RestController
public class HelloWorldController {

    @SuppressWarnings("checkstyle:Indentation")
    @GetMapping
    public List<String> getHelloWorld() {
        return List.of("Hello", "World");
    }
}
