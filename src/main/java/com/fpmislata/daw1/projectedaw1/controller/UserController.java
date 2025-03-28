package com.fpmislata.daw1.projectedaw1.controller;

import com.fpmislata.daw1.projectedaw1.common.container.LlibreIoc;
import com.fpmislata.daw1.projectedaw1.common.container.RessenyaIoc;
import com.fpmislata.daw1.projectedaw1.common.container.UsuariIoc;
import com.fpmislata.daw1.projectedaw1.common.container.ValoracioIoc;
import com.fpmislata.daw1.projectedaw1.controller.components.valoraciocard.LlibreValoracioCardMapper;
import com.fpmislata.daw1.projectedaw1.controller.components.valoraciocard.ValoracioCard;
import com.fpmislata.daw1.projectedaw1.domain.entity.EstadistiquesValoracio;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.domain.service.LlibreService;
import com.fpmislata.daw1.projectedaw1.domain.service.RessenyaService;
import com.fpmislata.daw1.projectedaw1.domain.service.UsuariService;
import com.fpmislata.daw1.projectedaw1.domain.service.ValoracioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
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

    @GetMapping("/usuari/{username}")
    public String llibre(Model model, @PathVariable(value = "username") String username) {
        Usuari usuari = usuariService.findByUsername(username);
        model.addAttribute("usuari", usuari);

        if (usuari == null) {
            return "error/404";
        }

        List<Valoracio> valoracions = valoracioService.findByUsuari(usuari);
        EstadistiquesValoracio estadistiques = new EstadistiquesValoracio(valoracions);
        model.addAttribute("estadistiques", estadistiques);

        List<ValoracioCard> valoracioCards = valoracions.stream()
                .map(
                    valoracio -> {
                        String isbn = valoracio.getIsbn();
                        Llibre llibre = llibreService.findByIsbn(isbn);
                        double mitjanaValoracions = valoracioService.getMitjanaByLlibre(llibre);
                        return LlibreValoracioCardMapper.map(llibre, mitjanaValoracions, valoracio);
                    }
                )
                .sorted(Comparator.comparing(ValoracioCard::getData).reversed())
                .toList();
        model.addAttribute("valoracioCards", valoracioCards);

        int nombreRessenyes = ressenyaService.countByUsuari(usuari);
        model.addAttribute("nombreRessenyes", nombreRessenyes);

        return "user/user";
    }
}
