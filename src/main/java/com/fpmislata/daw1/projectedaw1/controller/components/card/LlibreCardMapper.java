package com.fpmislata.daw1.projectedaw1.controller.components.card;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;

import java.util.Objects;

public class LlibreCardMapper {
    public static Card map(Llibre llibre, String subtitol) {
        Card card = new Card();
        card.setTitol(llibre.getTitol());
        card.setSubtitol(subtitol);
        card.setUrl("/llibre/" + llibre.getIsbn());

        String imatgeUrl = Objects.requireNonNullElse(
                llibre.getRutaImatge(),
                "placeholder.png"
        );
        card.setImatgeUrl("/files/llibre/" + imatgeUrl);
        return card;
    }
}
