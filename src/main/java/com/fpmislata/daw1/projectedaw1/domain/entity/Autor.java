package com.fpmislata.daw1.projectedaw1.domain.entity;

import com.fpmislata.daw1.projectedaw1.common.container.LlibreIoc;
import com.fpmislata.daw1.projectedaw1.domain.service.LlibreService;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Setter
@Getter
public class Autor {
    private int id;
    private String nom;
    private String biografia;
    private LocalDate dataNaixement;
    private String rutaImatge;

    private List<Llibre> llibres;

    public Autor(int id, String nom, String biografia, LocalDate dataNaixement, String rutaImatge) {
        this.id = id;
        this.nom = nom;
        this.biografia = biografia;
        this.dataNaixement = dataNaixement;
        this.rutaImatge = rutaImatge;
    }

    public Autor() {
    }

    public List<Llibre> getLlibres() {
        if (llibres == null) {
            LlibreService llibreService = LlibreIoc.createService();
            llibres = llibreService.findByAutor(this);
        }
        return llibres;
    }

    public String getPrettyDataNaixement() {
        return dataNaixement.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
