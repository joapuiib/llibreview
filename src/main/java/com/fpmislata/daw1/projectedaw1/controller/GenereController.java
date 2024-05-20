package com.fpmislata.daw1.projectedaw1.controller;

import com.fpmislata.daw1.projectedaw1.common.container.GenereIoc;
import com.fpmislata.daw1.projectedaw1.controller.components.CardItem;
import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.domain.service.GenereService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
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
        List<Genere> generes = genereService.findAll().stream()
                .sorted(Comparator.comparing(Genere::getNom))
                .toList();
        model.addAttribute("generes", generes);

        return "genere/generes";
    }

    @GetMapping("/genere/{id}")
    public String llibre(Model model, @PathVariable(value = "id") int id) {
        Genere genere = genereService.findById(id);
        model.addAttribute("genere", genere);

        List<CardItem> llibres = genere.getLlibres().stream()
                // .sorted(Comparator.comparing(Autor::getNom))
                .map(
                        llibre -> {
                            CardItem card = new CardItem();
                            card.setTitol(llibre.getTitol());
                            card.setUrl("/llibre/" + llibre.getIsbn());
                            card.setImatgeUrl("/img/llibre/" + (llibre.getRutaImatge() != null ? llibre.getRutaImatge() : "placeholder.png"));
                            return card;
                        }).toList();
        model.addAttribute("llibres", llibres);
        return "genere/genere";
    }
}
