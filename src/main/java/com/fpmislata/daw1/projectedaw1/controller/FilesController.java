package com.fpmislata.daw1.projectedaw1.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FilesController {

    private static final Logger LOG = LoggerFactory.getLogger(FilesController.class);

    @Value("${files.directory}")
    private String filesDirectory;
    public FilesController() {
    }

    @GetMapping("/files/**")
    public ResponseEntity<Resource> load(HttpServletRequest request) {
        String filename = request
                .getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE)
                .toString()
                .replace("/files/", "");
        Path path = Paths.get(filesDirectory).resolve(filename);

        try {
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok().header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\""
                ).body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
