package com.example.assessment.controller;

import com.example.assessment.Service.GetNameService;
import com.example.assessment.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private GetNameService getNameService;

    @GetMapping
    public ResponseEntity<Response> getHello(@RequestParam(required = false) String name) {
        String message = getNameService.GetName(name);
        if (message == null) {
            return ResponseEntity.badRequest().body(new Response(null, "Invalid Input"));
        }
        return ResponseEntity.ok(new Response(message, null));
    }
}
