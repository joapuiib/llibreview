package com.fpmislata.daw1.projectedaw1.controller;

import com.fpmislata.daw1.projectedaw1.common.container.ValoracioIoc;
import com.fpmislata.daw1.projectedaw1.controller.components.Alert;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.domain.service.ValoracioService;
import com.fpmislata.daw1.projectedaw1.security.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class ValoracioController {

    private final ValoracioService valoracioService;

    public ValoracioController() {
        valoracioService = ValoracioIoc.createService();
    }

    @PostMapping("/valoracio")
    public String login(@RequestParam("isbn") String isbn,
                        @RequestParam("my-valoracio") int valoracio,
                        RedirectAttributes redirectAttributes) {

        if (!UserSession.isUserLoggedIn()) {
            return "redirect:/llibre/" + isbn;
        }

        List<Alert> alerts = new ArrayList<>();

        String username = Objects.requireNonNull(UserSession.getUser()).getUsername();
        if (valoracio == -1) {
            valoracioService.delete(isbn, username);
            alerts.add(new Alert("info", "S'ha eliminat la valoració"));
            redirectAttributes.addFlashAttribute("alerts", alerts);
            return "redirect:/llibre/" + isbn;
        }

        LocalDate now = LocalDate.now();
        Valoracio valoracioObject = new Valoracio(isbn, username, valoracio, now);
        valoracioService.save(valoracioObject);
        alerts.add(new Alert("success", "S'ha guardat la valoració"));

        redirectAttributes.addFlashAttribute("alerts", alerts);
        return "redirect:/llibre/" + isbn;
    }
}
