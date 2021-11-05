package com.kitchen.iChef.Controller;

import com.kitchen.iChef.Controller.Request.LogInRequest;
import com.kitchen.iChef.Domain.Token;
import com.kitchen.iChef.Service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user/login")
public class LoginController {
    private static final Logger LOGGER = LogManager.getLogger();

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Token login(@Valid @RequestBody LogInRequest logInRequest) throws Exception {
        LOGGER.info("Logging in {}", logInRequest.getEmail());
        return userService.login(logInRequest.getEmail(), logInRequest.getPassword());
    }
}
