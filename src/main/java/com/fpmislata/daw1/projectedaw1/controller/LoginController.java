package com.fpmislata.daw1.projectedaw1.controller;

import com.fpmislata.daw1.projectedaw1.common.container.UserIoc;
import com.fpmislata.daw1.projectedaw1.controller.components.CardItem;
import com.fpmislata.daw1.projectedaw1.domain.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController() {
        userService = UserIoc.getUserService();
    }

    @SuppressWarnings("SameReturnValue")
    @GetMapping("/login")
    public String index(Model model) {
        return "login/login";
    }
}
