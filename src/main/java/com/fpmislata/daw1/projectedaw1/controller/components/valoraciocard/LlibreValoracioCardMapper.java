package com.fpmislata.daw1.projectedaw1.controller.components.valoraciocard;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;

import java.util.Objects;

public class LlibreValoracioCardMapper {
    public static ValoracioCard map(
            Llibre llibre,
            double mitjanaValoracions,
            Valoracio valoracio
    ) {
        ValoracioCard card = new ValoracioCard();
        card.setUrl("/llibre/" + llibre.getIsbn());

        String imatgeUrl = Objects.requireNonNullElse(
                llibre.getRutaImatge(),
                "placeholder.png"
        );
        card.setImatgeUrl("/files/llibre/" + imatgeUrl);

        card.setPuntuacio(valoracio.getPuntuacio());
        card.setMitjana(mitjanaValoracions);
        return card;
    }
}
