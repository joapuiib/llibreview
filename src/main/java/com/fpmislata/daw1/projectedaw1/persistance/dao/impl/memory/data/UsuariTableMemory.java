package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data;

import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.UsuariRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuariTableMemory {
    private final List<UsuariRecord> usuariRecordList = new ArrayList<>(List.of(
            new UsuariRecord("admin", "admin@localhost", LocalDate.parse("2021-01-01"), "admin"),
            new UsuariRecord("user1", "user@localhost", LocalDate.parse("2021-01-01"), "user1"),
            new UsuariRecord("user2", "user2@localhost", LocalDate.parse("2021-01-01"), "user2")
    ));

    public List<UsuariRecord> get(){
        return usuariRecordList;
    }

    public void add(UsuariRecord usuariRecord){
        usuariRecordList.add(usuariRecord);
    }
}
