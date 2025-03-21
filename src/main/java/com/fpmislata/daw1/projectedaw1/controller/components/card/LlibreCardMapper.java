package com.fpmislata.daw1.projectedaw1.controller.components.card;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;

public class LlibreCardMapper {
    public static Card map(Llibre llibre, String subtitol) {
        Card card = new Card();
        card.setTitol(llibre.getTitol());
        card.setSubtitol(subtitol);
        card.setUrl("/llibre/" + llibre.getIsbn());
        card.setImatgeUrl("/files/llibre/" + (llibre.getRutaImatge() != null ? llibre.getRutaImatge() : "placeholder.png"));
        return card;
    }
}
