package com.example.assessment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/hello-world")
public class GetNameController {
//this is the controller
    @GetMapping
    public ResponseEntity<Map<String, String>> GetHello(@RequestParam(required = false) String name) {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid Input"));
        }

        char firstChar = Character.toUpperCase(name.charAt(0));
        if (firstChar >= 'A' && firstChar <= 'M') {
            String capitalized = Character.toUpperCase(name.charAt(0)) + name.substring(1);
            return ResponseEntity.ok(Map.of("message", "Hello " + capitalized));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Invalid Input"));
    }
}
