package com.fpmislata.daw1.projectedaw1.data;

import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;

import java.time.LocalDate;
import java.util.List;

public class UsuariData {
    public static final List<Usuari> USUARI_LIST = List.of(
            new Usuari("user1", "user1@localhost", LocalDate.parse("2021-01-01")),
            new Usuari("user2", "user2@localhost", LocalDate.parse("2021-01-01")),
            new Usuari("user3", "user3@localhost", LocalDate.parse("2021-01-01")),
            new Usuari("user4", "user4@localhost", LocalDate.parse("2021-01-01"))
    );
}
