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
public class AutorController {

    private final AutorService autorService;
    private final LlibreService llibreService;

    public AutorController() {
        this.autorService = AutorIoc.createService();
        this.llibreService = LlibreIoc.createService();
    }

    @SuppressWarnings("SameReturnValue")
    @GetMapping("/autor")
    public String index(Model model) {
        List<CardItem> autors = autorService.findAll().stream().map(
                autor -> {
                     CardItem card = new CardItem();
                     card.setTitol(autor.getNom());
                     card.setUrl("/autor/" + autor.getId());
                     card.setImatgeUrl("/img/autor/" + (autor.getRutaImatge() != null ? autor.getRutaImatge() : "fallback.png"));
                     return card;
                }).toList();
        model.addAttribute("autors", autors);

        return "autor/autors";
    }

    @GetMapping("/autor/{id}")
    public String autor(Model model, @PathVariable(value = "id") int id) {
        Autor autor = autorService.findById(id);
        model.addAttribute("autor", autor);

        List<CardItem> llibres = llibreService.findByAutor(autor).stream()
                .sorted(Comparator.comparing(Llibre::getDataPublicacio).reversed())
                .map(
                    llibre -> {
                        CardItem card = new CardItem();
                        card.setTitol(llibre.getTitol());
                        card.setSubtitol(Integer.toString(llibre.getDataPublicacio().getYear()));
                        card.setUrl("/llibre/" + llibre.getIsbn());
                        card.setImatgeUrl("/img/llibre/" + (llibre.getRutaImatge() != null ? llibre.getRutaImatge() : "fallback.png"));
                        return card;
                    }).toList();
        model.addAttribute("llibres", llibres);
        return "autor/autor";
    }
}
