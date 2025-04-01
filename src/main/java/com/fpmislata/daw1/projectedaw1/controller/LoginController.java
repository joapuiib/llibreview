package com.fpmislata.daw1.projectedaw1.controller;

import com.fpmislata.daw1.projectedaw1.common.container.UsuariIoc;
import com.fpmislata.daw1.projectedaw1.controller.components.Alert;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.domain.service.UsuariService;
import com.fpmislata.daw1.projectedaw1.security.UserSession;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
    private final UsuariService usuariService;

    public LoginController() {
        usuariService = UsuariIoc.createService();
    }

    @SuppressWarnings("SameReturnValue")
    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        if (UserSession.isUserLoggedIn()) {
            return "redirect:/";
        }
        String referer = request.getHeader("Referer");
        model.addAttribute("referer", referer);

        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam(value = "referer") String referer,
                        Model model) {

        if (UserSession.isUserLoggedIn()) {
            return "redirect:/";
        }

        List<Alert> alerts = new ArrayList<>();

        boolean success = usuariService.login(username, password);
        if (success) {
            return referer != null ? "redirect:" + referer : "redirect:/";
        }

        alerts.add(new Alert("danger", "No s'ha trobat l'usuari o la contrasenya no és correcta."));
        model.addAttribute("alerts", alerts);
        model.addAttribute("referer", referer);
        return "login/login";
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
            Usuari usuari = new Usuari(username, email);
            usuariService.create(usuari, password, passwordConfirmation);
            alerts.add(new Alert("success", "L'usuari s'ha registrat correctament."));
        } catch (RuntimeException exception) {
            alerts.add(new Alert("danger", exception.getMessage()));
        }

        redirectAttributes.addFlashAttribute("alerts", alerts);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        UserSession.clear();
        String referer = request.getHeader("Referer");
        return referer != null ? "redirect:" + referer : "redirect:/";
    }
}
