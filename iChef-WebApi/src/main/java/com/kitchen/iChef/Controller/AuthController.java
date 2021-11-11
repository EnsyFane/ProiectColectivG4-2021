package com.kitchen.iChef.Controller;

import com.kitchen.iChef.Controller.Model.Request.SignUpRequest;
import com.kitchen.iChef.Controller.Mapper.SignUpMapper;
import com.kitchen.iChef.Controller.Model.Request.LogInRequest;
import com.kitchen.iChef.Domain.Token;
import com.kitchen.iChef.Service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class AuthController {
    private static final Logger LOGGER = LogManager.getLogger();

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/sign-up")
    public void signUp(@RequestBody @Valid SignUpRequest signUpRequest) throws Exception {
        LOGGER.info("Signing up {}", signUpRequest.getEmail());
        userService.signUp(SignUpMapper.mapFromRequest(signUpRequest));
    }

    @PostMapping(value = "/login")
    public Token login(@Valid @RequestBody LogInRequest logInRequest) throws Exception {
        LOGGER.info("Logging in {}", logInRequest.getEmail());
        return userService.login(logInRequest.getEmail(), logInRequest.getPassword());
    }
}
