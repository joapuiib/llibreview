package com.fpmislata.daw1.projectedaw1.controller.components.card;

import java.util.Objects;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;

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
