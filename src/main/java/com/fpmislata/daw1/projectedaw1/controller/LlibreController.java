package com.fpmislata.daw1.projectedaw1.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fpmislata.daw1.projectedaw1.common.container.AutorIoc;
import com.fpmislata.daw1.projectedaw1.common.container.GenereIoc;
import com.fpmislata.daw1.projectedaw1.common.container.LlibreIoc;
import com.fpmislata.daw1.projectedaw1.common.container.RessenyaIoc;
import com.fpmislata.daw1.projectedaw1.common.container.ValoracioIoc;
import com.fpmislata.daw1.projectedaw1.controller.components.card.AutorCardMapper;
import com.fpmislata.daw1.projectedaw1.controller.components.card.Card;
import com.fpmislata.daw1.projectedaw1.controller.components.card.LlibreCardMapper;
import com.fpmislata.daw1.projectedaw1.domain.entity.EstadistiquesValoracio;
import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.domain.service.AutorService;
import com.fpmislata.daw1.projectedaw1.domain.service.GenereService;
import com.fpmislata.daw1.projectedaw1.domain.service.LlibreService;
import com.fpmislata.daw1.projectedaw1.domain.service.RessenyaService;
import com.fpmislata.daw1.projectedaw1.domain.service.ValoracioService;
import com.fpmislata.daw1.projectedaw1.security.UserSession;

@Controller
public class LlibreController {

    private final LlibreService llibreService;
    private final AutorService autorService;
    private final GenereService genereService;
    private final ValoracioService valoracioService;
    private final RessenyaService ressenyaService;

    public LlibreController() {
        this.llibreService = LlibreIoc.createService();
        this.autorService = AutorIoc.createService();
        this.genereService = GenereIoc.createService();
        this.valoracioService = ValoracioIoc.createService();
        this.ressenyaService = RessenyaIoc.createService();
    }

    @GetMapping("/llibre")
    public String index(Model model) {
        List<Card> llibres = llibreService.findAll().stream().map(
                llibre -> LlibreCardMapper.map(llibre, "ISBN: " + llibre.getIsbn())).toList();
        model.addAttribute("llibres", llibres);

        return "llibre/llibres";
    }

    @GetMapping("/llibre/{isbn}")
    public String llibre(Model model, @PathVariable(value = "isbn") String isbn) {
        Llibre llibre = llibreService.findByIsbn(isbn);
        model.addAttribute("llibre", llibre);

        List<Card> autors = autorService.findByLlibre(llibre).stream()
                .map(AutorCardMapper::map).toList();
        model.addAttribute("autors", autors);

        List<Genere> generes = genereService.findByLlibre(llibre);
        model.addAttribute("generes", generes);

        List<Valoracio> valoracions = valoracioService.findByLlibre(llibre);
        model.addAttribute("stats", new EstadistiquesValoracio(valoracions));

        List<Ressenya> ressenyes = ressenyaService.findByLlibre(llibre);
        model.addAttribute("ressenyes", ressenyes);

        Usuari usuari = UserSession.getUser();
        if (usuari != null) {
            Ressenya ressenyaUsuari = ressenyaService.findByLlibreAndUsuari(llibre, usuari);
            model.addAttribute("ressenyaUsuari", ressenyaUsuari);
            if (ressenyaUsuari != null) {
                model.addAttribute("valoracioUsuari", ressenyaUsuari.getValoracio());
            } else {
                Valoracio valoracioUsuari = valoracioService.findByLlibreAndUsuari(llibre, usuari);
                model.addAttribute("valoracioUsuari", valoracioUsuari);
            }
        }

        return "llibre/llibre";
    }
}
