package com.fpmislata.daw1.projectedaw1.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpmislata.daw1.projectedaw1.common.container.LlibreIoc;
import com.fpmislata.daw1.projectedaw1.common.container.RessenyaIoc;
import com.fpmislata.daw1.projectedaw1.common.container.ValoracioIoc;
import com.fpmislata.daw1.projectedaw1.controller.components.Alert;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.domain.entity.ValoracioStats;
import com.fpmislata.daw1.projectedaw1.domain.service.LlibreService;
import com.fpmislata.daw1.projectedaw1.domain.service.RessenyaService;
import com.fpmislata.daw1.projectedaw1.domain.service.ValoracioService;
import com.fpmislata.daw1.projectedaw1.security.UserSession;

@Controller
public class RessenyaController {

    private final LlibreService llibreService;
    private final ValoracioService valoracioService;
    private final RessenyaService ressenyaService;

    public RessenyaController() {
        this.llibreService = LlibreIoc.createService();
        this.valoracioService = ValoracioIoc.createService();
        this.ressenyaService = RessenyaIoc.createService();
    }

    @GetMapping("/ressenya/{isbn}")
    public String ressenya(Model model, @PathVariable(value = "isbn") String isbn) {
        Llibre llibre = llibreService.findByIsbn(isbn);
        model.addAttribute("llibre", llibre);

        ValoracioStats stats = new ValoracioStats(valoracioService.findByLlibre(llibre));
        model.addAttribute("valoracioStats", stats);

        if (!UserSession.isUserLoggedIn()) {
            return "redirect:/llibre/" + isbn;
        }

        Valoracio valoracio = valoracioService.findByLlibreAndUser(llibre, UserSession.getUser());
        model.addAttribute("valoracioUsuari", valoracio);
        return "ressenya/ressenya";
    }

    @PostMapping("/ressenya")
    public String edit(@RequestParam("isbn") String isbn,
                        @RequestParam("comentari") String comentari,
                        @RequestParam("my-valoracio") int valoracio,
                        RedirectAttributes redirectAttributes) {

        if (!UserSession.isUserLoggedIn()) {
            return "redirect:/llibre/" + isbn;
        }

        List<Alert> alerts = new ArrayList<>();

        Usuari usuari = Objects.requireNonNull(UserSession.getUser());
        Llibre llibre = llibreService.findByIsbn(isbn);
        Valoracio oldValoracio = valoracioService.findByLlibreAndUser(llibre, usuari);

        if (valoracio == -1) {
            valoracioService.delete(isbn, usuari.getUsername());
            alerts.add(new Alert("info", "S'ha eliminat la valoració"));
            redirectAttributes.addFlashAttribute("alerts", alerts);
            return "redirect:/llibre/" + isbn;
        } else if (oldValoracio == null || oldValoracio.getPuntuacio() != valoracio) {
            LocalDate now = LocalDate.now();
            Valoracio valoracioObject = new Valoracio(isbn, usuari.getUsername(), valoracio, now);
            valoracioService.save(valoracioObject);
            alerts.add(new Alert("success", "S'ha guardat la nova valoració"));
        }

        if (comentari != null && !comentari.isEmpty()) {
            LocalDate now = LocalDate.now();
            Ressenya ressenya = new Ressenya(isbn, usuari.getUsername(), comentari, now);
            ressenyaService.save(ressenya);
            alerts.add(new Alert("success", "S'ha guardat la ressenya"));
        }

        redirectAttributes.addFlashAttribute("alerts", alerts);
        return "redirect:/llibre/" + isbn;
    }

    @GetMapping("/ressenya/{isbn}/delete")
    public String delete(Model model,
                         @PathVariable(value = "isbn") String isbn,
                         RedirectAttributes redirectAttributes) {
        List<Alert> alerts = new ArrayList<>();

        if (!UserSession.isUserLoggedIn()) {
            return "redirect:/llibre/" + isbn;
        }

        Usuari usuari = Objects.requireNonNull(UserSession.getUser());
        ressenyaService.delete(isbn, usuari.getUsername());
        alerts.add(new Alert("success", "S'ha eliminat la ressenya"));
        redirectAttributes.addFlashAttribute("alerts", alerts);
        return "redirect:/llibre/" + isbn;
    }
}
