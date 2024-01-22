package com.fpmislata.daw1.projectedaw1.controller;

import com.fpmislata.daw1.projectedaw1.common.factory.LlibreFactory;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.LlibreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    private LlibreService llibreService;

    public MainController() {
        this.llibreService = LlibreFactory.createLlibreService();
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Llibre> ultimsLlibres = llibreService.findAll();
        System.out.println(ultimsLlibres);
        model.addAttribute("ultimsLlibres", ultimsLlibres);
        return "index";
    }
}
