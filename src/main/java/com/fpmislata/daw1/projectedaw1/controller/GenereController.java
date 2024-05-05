package com.fpmislata.daw1.projectedaw1.controller;

import com.fpmislata.daw1.projectedaw1.common.container.GenereIoc;
import com.fpmislata.daw1.projectedaw1.controller.components.CardItem;
import com.fpmislata.daw1.projectedaw1.domain.service.GenereService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GenereController {

    private final GenereService genereService;

    public GenereController() {
        this.genereService = GenereIoc.createService();
    }

    @SuppressWarnings("SameReturnValue")
    @GetMapping("/genere")
    public String index(Model model) {
        List<CardItem> autors = genereService.findAll().stream().map(
                genere -> {
                     CardItem card = new CardItem();
                     card.setTitol(genere.getNom());
                     card.setUrl("/genere/" + genere.getId());
                     card.setImatgeUrl("/img/genere/" + (genere.getRutaImatge() != null ? genere.getRutaImatge() : "fallback.png"));
                     return card;
                }).toList();
        model.addAttribute("generes", autors);

        return "genere/generes";
    }
}
