package com.fpmislata.daw1.projectedaw1.controller;

import com.fpmislata.daw1.projectedaw1.common.container.LlibreIoc;
import com.fpmislata.daw1.projectedaw1.controller.components.CardItem;
import com.fpmislata.daw1.projectedaw1.domain.service.LlibreService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Log4j2
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
                     card.setSubtitol(llibre.getDataPublicacio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                     card.setUrl("/llibre/" + llibre.getIsbn());
                     card.setImatgeUrl("/img/llibre/" + (llibre.getRutaImatge() != null ? llibre.getRutaImatge() : "placeholder.png"));
                     return card;
                }).toList();
        model.addAttribute("ultimsLlibres", ultimsLlibres);

        List<CardItem> mesLlegits = llibreService.findMostRead(4).stream().map(
                llibre -> {
                    CardItem card = new CardItem();
                    card.setTitol(llibre.getTitol());
                    card.setSubtitol("Lectors: " + llibre.getNombreValoracios());
                    card.setUrl("/llibre/" + llibre.getIsbn());
                    card.setImatgeUrl("/img/llibre/" + (llibre.getRutaImatge() != null ? llibre.getRutaImatge() : "placeholder.png"));
                    return card;
                }).toList();
        model.addAttribute("mesLlegits", mesLlegits);

        List<CardItem> millorValorats = llibreService.findBestRated(4).stream().map(
                llibre -> {
                    CardItem card = new CardItem();
                    card.setTitol(llibre.getTitol());
                    card.setSubtitol("Valoració: " + String.format("%.1f", llibre.getAverageValoracio()));
                    card.setUrl("/llibre/" + llibre.getIsbn());
                    card.setImatgeUrl("/img/llibre/" + (llibre.getRutaImatge() != null ? llibre.getRutaImatge() : "placeholder.png"));
                    return card;
                }).toList();
        model.addAttribute("millorValorats", millorValorats);

        log.info("Current language: {}", LocaleContextHolder.getLocale().getLanguage());

        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
