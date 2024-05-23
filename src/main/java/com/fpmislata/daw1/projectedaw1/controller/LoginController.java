package com.fpmislata.daw1.projectedaw1.controller;

import com.fpmislata.daw1.projectedaw1.common.container.UsuariIoc;
import com.fpmislata.daw1.projectedaw1.controller.components.Alert;
import com.fpmislata.daw1.projectedaw1.domain.service.UsuariService;
import com.fpmislata.daw1.projectedaw1.security.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    private final UsuariService usuariService;

    public LoginController() {
        usuariService = UsuariIoc.getUserService();
    }

    @SuppressWarnings("SameReturnValue")
    @GetMapping("/login")
    public String login() {
        if (UserSession.isUserLoggedIn()) {
            return "redirect:/";
        }

        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        RedirectAttributes redirectAttributes) {

        if (UserSession.isUserLoggedIn()) {
            return "redirect:/";
        }

        List<Alert> alerts = new ArrayList<>();

        try {
            usuariService.login(username, password);
            return "redirect:/";
        } catch (RuntimeException exception) {
            alerts.add(new Alert("danger", exception.getMessage()));
        }

        redirectAttributes.addFlashAttribute("alerts", alerts);
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String register() {
        if (UserSession.isUserLoggedIn()) {
            return "redirect:/";
        }

        return "login/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("password-confirmation") String passwordConfirmation,
                           RedirectAttributes redirectAttributes) {

        if (UserSession.isUserLoggedIn()) {
            return "redirect:/";
        }

        List<Alert> alerts = new ArrayList<>();

        try {
            usuariService.create(username, email, password, passwordConfirmation);
            alerts.add(new Alert("success", "L'usuari s'ha registrat correctament."));
        } catch (RuntimeException exception) {
            alerts.add(new Alert("danger", exception.getMessage()));
        }

        redirectAttributes.addFlashAttribute("alerts", alerts);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout() {
        UserSession.clear();
        return "redirect:/";
    }
}
