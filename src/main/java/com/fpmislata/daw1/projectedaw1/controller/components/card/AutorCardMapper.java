package com.fpmislata.daw1.projectedaw1.controller.components.card;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;

public class AutorCardMapper {
    public static Card map(Autor autor) {
        Card card = new Card();
        card.setTitol(autor.getNom());
        card.setUrl("/autor/" + autor.getId());
        card.setImatgeUrl("/files/autor/" + (
                autor.getRutaImatge() != null ? autor.getRutaImatge() : "fallback.png"
            ));
        return card;
    }
}
