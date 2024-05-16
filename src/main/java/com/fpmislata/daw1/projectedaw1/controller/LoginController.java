package com.fpmislata.daw1.projectedaw1.controller;

import com.fpmislata.daw1.projectedaw1.common.container.UserIoc;
import com.fpmislata.daw1.projectedaw1.controller.components.Alert;
import com.fpmislata.daw1.projectedaw1.domain.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController() {
        userService = UserIoc.getUserService();
    }

    @SuppressWarnings("SameReturnValue")
    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @GetMapping("/register")
    public String register() {
        return "login/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("password-confirmation") String passwordConfirmation,
                           RedirectAttributes redirectAttributes) {

        List<Alert> alerts = new ArrayList<>();
        if (!password.equals(passwordConfirmation)) {
            alerts.add(new Alert("danger", "Les contrasenyes no coincideixen"));
        }

        redirectAttributes.addFlashAttribute("alerts", alerts);
        return "redirect:/register";
    }
}
