package com.urlshortener.urlshortener;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class ErrorController {
    
    @GetMapping("/error")
    public ResponseEntity<String> getError(){
        return new ResponseEntity<String>("Algo ha ido mal", HttpStatus.NOT_FOUND);
    } 

}
