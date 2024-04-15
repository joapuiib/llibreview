package com.fpmislata.daw1.projectedaw1.controller;

import com.fpmislata.daw1.projectedaw1.common.container.AutorIoc;
import com.fpmislata.daw1.projectedaw1.common.container.LlibreIoc;
import com.fpmislata.daw1.projectedaw1.controller.components.CardItem;
import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.AutorService;
import com.fpmislata.daw1.projectedaw1.domain.service.LlibreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.List;

@Controller
public class LlibreController {

    private final LlibreService llibreService;
    private final AutorService autorService;

    public LlibreController() {
        this.llibreService = LlibreIoc.createService();
        this.autorService = AutorIoc.createService();
    }

    @SuppressWarnings("SameReturnValue")
    @GetMapping("/llibre")
    public String index(Model model) {
        List<CardItem> llibres = llibreService.findAll().stream().map(
                llibre -> {
                     CardItem card = new CardItem();
                     card.setTitol(llibre.getTitol());
                     card.setSubtitol("ISBN: " + llibre.getIsbn());
                     card.setUrl("/llibre/" + llibre.getIsbn());
                     card.setImatgeUrl("/img/llibre/" + (llibre.getRutaImatge() != null ? llibre.getRutaImatge() : "placeholder.png"));
                     return card;
                }).toList();
        model.addAttribute("llibres", llibres);

        return "llibre/llibres";
    }

    @GetMapping("/llibre/{isbn}")
    public String llibre(Model model, @PathVariable(value = "isbn") String isbn) {
        Llibre llibre = llibreService.findByIsbn(isbn);
        model.addAttribute("llibre", llibre);

        List<CardItem> autors = autorService.findByLlibre(llibre).stream()
                // .sorted(Comparator.comparing(Autor::getNom))
                .map(
                        autor -> {
                            CardItem card = new CardItem();
                            card.setTitol(autor.getNom());
                            card.setUrl("/autor/" + autor.getId());
                            card.setImatgeUrl("/img/autor/" + (autor.getRutaImatge() != null ? autor.getRutaImatge() : "placeholder.png"));
                            return card;
                        }).toList();

        System.out.println(autors);
        model.addAttribute("autors", autors);
        return "llibre/llibre";
    }
}