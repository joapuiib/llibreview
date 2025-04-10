package com.fpmislata.daw1.projectedaw1.controller;

import com.fpmislata.daw1.projectedaw1.common.container.GenereIoc;
import com.fpmislata.daw1.projectedaw1.common.container.LlibreIoc;
import com.fpmislata.daw1.projectedaw1.controller.components.card.Card;
import com.fpmislata.daw1.projectedaw1.controller.components.card.LlibreCardMapper;
import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.domain.service.GenereService;
import com.fpmislata.daw1.projectedaw1.domain.service.LlibreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.List;

@Controller
public class GenereController {

    private final GenereService genereService;
    private final LlibreService llibreService;

    public GenereController() {
        this.genereService = GenereIoc.createService();
        this.llibreService = LlibreIoc.createService();
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

        List<Card> llibres = llibreService.findByGenere(genere).stream()
                .map(
                        llibre -> LlibreCardMapper.map(llibre, "")
                ).toList();
        model.addAttribute("llibres", llibres);
        return "genere/genere";
    }
}
