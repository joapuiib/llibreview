package com.fpmislata.daw1.projectedaw1.data;

import com.fpmislata.daw1.projectedaw1.common.i18n.Language;
import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;

import java.util.List;

public class GenereData {
    public static final List<Genere> GENERE_LIST = List.of(
            new Genere( 1, "Genere 1"),
            new Genere( 2, "Genere 2"),
            new Genere( 3, "Genere 3")
    );
    public static final List<Genere> GENERE_LIST_EN = List.of(
            new Genere( 1, "Genre 1"),
            new Genere( 2, "Genre 2"),
            new Genere( 3, "Genre 3")
    );

    public static List<Genere> getGenereList(Language language) {
        if (language.getPrefix().equals("en")) {
            return GENERE_LIST_EN;
        } else {
            return GENERE_LIST;
        }
    }
}
