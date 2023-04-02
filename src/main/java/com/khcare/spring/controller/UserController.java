package com.khcare.spring.controller;

import com.khcare.spring.dto.LoginDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        logger.info(loginDto+"");

        return null;
    }

}
