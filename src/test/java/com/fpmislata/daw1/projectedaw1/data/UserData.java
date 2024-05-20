package com.fpmislata.daw1.projectedaw1.data;

import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.UsuariRecord;

import java.time.LocalDate;
import java.util.List;

public class UserData {
    public static final List<Usuari> USUARI_LIST = List.of(
            new Usuari("admin", "admin@localhost", LocalDate.parse("2021-01-01")),
            new Usuari("user", "user@localhost", LocalDate.parse("2021-01-01")),
            new Usuari("user2", "user2@localhost", LocalDate.parse("2021-01-01"))
    );

    public static final List<UsuariRecord> USER_RECORD_LIST = List.of(
            new UsuariRecord("admin", "admin@localhost", LocalDate.parse("2021-01-01"), "admin"),
            new UsuariRecord("user", "user@localhost", LocalDate.parse("2021-01-01"), "user"),
            new UsuariRecord("user2", "user2@localhost", LocalDate.parse("2021-01-01"), "user2")
    );
}
