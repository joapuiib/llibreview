package com.fpmislata.daw1.projectedaw1.controller;

import com.fpmislata.daw1.projectedaw1.common.container.*;
import com.fpmislata.daw1.projectedaw1.controller.components.card.AutorCardMapper;
import com.fpmislata.daw1.projectedaw1.controller.components.card.Card;
import com.fpmislata.daw1.projectedaw1.controller.components.card.LlibreCardMapper;
import com.fpmislata.daw1.projectedaw1.controller.components.valoraciocard.LlibreValoracioCardMapper;
import com.fpmislata.daw1.projectedaw1.controller.components.valoraciocard.ValoracioCard;
import com.fpmislata.daw1.projectedaw1.domain.entity.*;
import com.fpmislata.daw1.projectedaw1.domain.service.*;
import com.fpmislata.daw1.projectedaw1.security.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserController {
    private final UsuariService usuariService;
    private final RessenyaService ressenyaService;
    private final ValoracioService valoracioService;
    private final LlibreService llibreService;

    public UserController() {
        this.usuariService = UsuariIoc.createService();
        this.ressenyaService = RessenyaIoc.createService();
        this.valoracioService = ValoracioIoc.createService();
        this.llibreService = LlibreIoc.createService();
    }

    @GetMapping("/user/{username}")
    public String llibre(Model model, @PathVariable(value = "username") String username) {
        Usuari usuari = usuariService.findByUsername(username);
        model.addAttribute("usuari", usuari);

        if (usuari == null) {
            return "error/404";
        }

        List<Valoracio> valoracions = valoracioService.findByUsuari(usuari);
        EstadistiquesValoracio estadistiques = new EstadistiquesValoracio(valoracions);
        model.addAttribute("estadistiques", estadistiques);

        List<ValoracioCard> valoracioCards = valoracions.stream().map(
                valoracio -> {
                    String isbn = valoracio.getIsbn();
                    Llibre llibre = llibreService.findByIsbn(isbn);
                    double mitjanaValoracions = valoracioService.getMitjanaByLlibre(llibre);
                    return LlibreValoracioCardMapper.map(llibre, mitjanaValoracions, valoracio);
                }
        ).toList();
        model.addAttribute("valoracioCards", valoracioCards);

        int nombreRessenyes = ressenyaService.countByUsuari(usuari);
        model.addAttribute("nombreRessenyes", nombreRessenyes);

        return "user/user";
    }
}
