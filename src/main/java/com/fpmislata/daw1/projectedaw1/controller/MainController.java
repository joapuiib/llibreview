package com.fpmislata.daw1.projectedaw1.controller;

import com.fpmislata.daw1.projectedaw1.common.container.LlibreIoc;
import com.fpmislata.daw1.projectedaw1.controller.components.CardItem;
import com.fpmislata.daw1.projectedaw1.domain.service.LlibreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    private final LlibreService llibreService;

    public MainController() {
        this.llibreService = LlibreIoc.createService();
    }

    @SuppressWarnings("SameReturnValue")
    @GetMapping("/")
    public String index(Model model) {
        List<CardItem> ultimsLlibres = llibreService.findLatest(4).stream().map(
                llibre -> {
                     CardItem card = new CardItem();
                     card.setTitol(llibre.getTitol());
                     card.setSubtitol(llibre.getPrettyDataPublicacio());
                     card.setUrl("/llibre/" + llibre.getIsbn());
                     card.setImatgeUrl("/img/llibre/" + (llibre.getRutaImatge() != null ? llibre.getRutaImatge() : "fallback.png"));
                     return card;
                }).toList();
        model.addAttribute("ultimsLlibres", ultimsLlibres);

        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
