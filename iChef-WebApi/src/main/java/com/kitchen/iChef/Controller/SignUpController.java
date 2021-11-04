package com.kitchen.iChef.Controller;

import com.kitchen.iChef.Controller.Request.SignUpRequest;
import com.kitchen.iChef.Controller.Request.Transformer.SignUpRequestTransformer;
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
@RequestMapping("/user/sign-up")
public class SignUpController {
    private static final Logger LOGGER = LogManager.getLogger();

    private final UserService userService;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void signUp(@RequestBody @Valid SignUpRequest signUpRequest) throws Exception {
        LOGGER.info("Signing up {}", signUpRequest.getEmail());
        userService.signUp(SignUpRequestTransformer.transformFromRequest(signUpRequest));
    }
}
