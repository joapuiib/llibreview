package com.fpmislata.daw1.projectedaw1.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fpmislata.daw1.projectedaw1.domain.entity.EstadistiquesValoracio;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fpmislata.daw1.projectedaw1.common.container.LlibreIoc;
import com.fpmislata.daw1.projectedaw1.common.container.ValoracioIoc;
import com.fpmislata.daw1.projectedaw1.controller.components.card.Card;
import com.fpmislata.daw1.projectedaw1.controller.components.card.LlibreCardMapper;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.domain.service.LlibreService;
import com.fpmislata.daw1.projectedaw1.domain.service.ValoracioService;

@Log4j2
@Controller
public class MainController {

    private final LlibreService llibreService;
    private final ValoracioService valoracioService;

    public MainController() {
        this.llibreService = LlibreIoc.createService();
        this.valoracioService = ValoracioIoc.createService();
    }


    @SuppressWarnings("SameReturnValue")
    @GetMapping("/")
    public String index(Model model) {
        List<Card> ultimsLlibres = llibreService.findLatest(4).stream().map(
                llibre -> LlibreCardMapper.map(
                        llibre,
                        llibre.getDataPublicacio().format(
                                DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        )
                )
        ).toList();
        model.addAttribute("ultimsLlibres", ultimsLlibres);

        List<Card> mesLlegits = llibreService.findMostRead(4).stream().map(
                llibre -> {
                    List<Valoracio> valoracions = valoracioService.findByLlibre(llibre);
                    EstadistiquesValoracio estadistiquesValoracio = new EstadistiquesValoracio(valoracions);
                    return LlibreCardMapper.map(llibre, "Lectors: " + estadistiquesValoracio.getCount());
                }).toList();
        model.addAttribute("mesLlegits", mesLlegits);

        List<Card> millorValorats = llibreService.findBestRated(4).stream().map(
                llibre -> {
                    List<Valoracio> valoracions = valoracioService.findByLlibre(llibre);
                    EstadistiquesValoracio estadistiquesValoracio = new EstadistiquesValoracio(valoracions);
                    return LlibreCardMapper.map(
                            llibre,
                            String.format("Valoració: %.1f", estadistiquesValoracio.getAverage())
                    );
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
