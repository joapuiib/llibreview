package com.fpmislata.daw1.projectedaw1.common.utils;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.util.Map;

@Configuration
@Log4j2
public class ThymeleafConfig {

    @Autowired
    private ThymeleafViewResolver viewResolver;

    @Autowired
    private ThymeleafUtils thymeleafUtils;

    @PostConstruct
    public void configureTemplateEngine() {
        log.info("Configuring Thymeleaf template engine");
        viewResolver.setStaticVariables(Map.of("utils", thymeleafUtils));
    }
}