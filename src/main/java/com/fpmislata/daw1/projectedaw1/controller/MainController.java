package com.fpmislata.daw1.projectedaw1.controller;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.LlibreService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.LlibreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private LlibreService llibreService;

    @GetMapping("/")
    public String index(Model model) {
        List<Llibre> ultimsLlibres = llibreService.findLatest(4);
        System.out.println(ultimsLlibres);
        model.addAttribute("ultimsLlibres", ultimsLlibres);
        return "index";
    }
}
