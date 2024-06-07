package com.fpmislata.daw1.projectedaw1.presentation;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.testing.templateengine.engine.TestExecutor;

@SpringBootTest()
@ImportAutoConfiguration(ThymeleafAutoConfiguration.class)
public class IndexTest {
}
