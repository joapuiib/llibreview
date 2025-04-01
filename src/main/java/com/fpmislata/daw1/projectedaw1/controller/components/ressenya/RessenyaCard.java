package com.fpmislata.daw1.projectedaw1.controller.components.ressenya;

import com.fpmislata.daw1.projectedaw1.controller.components.card.Card;
import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RessenyaCard {
    private Card card;
    private Ressenya ressenya;

    public RessenyaCard(Card card, Ressenya ressenya) {
        this.card = card;
        this.ressenya = ressenya;
    }

    public RessenyaCard() {
    }

    public static RessenyaCard map(Ressenya ressenya, Card card) {
        return new RessenyaCard(card, ressenya);
    }
}
