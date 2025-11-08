package com.example.assessment.Service;

import org.springframework.stereotype.Service;

@Service
public class GetNameService {
    public String GetName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }

        char firstChar = Character.toUpperCase(name.charAt(0));
        if (firstChar >= 'A' && firstChar <= 'M') {
            return "Hello " + Character.toUpperCase(name.charAt(0)) + name.substring(1);
        }
        return null;
    }
}
