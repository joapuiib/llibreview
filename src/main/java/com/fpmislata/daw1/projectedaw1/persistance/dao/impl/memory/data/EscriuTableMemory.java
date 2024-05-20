package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data;

import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.AutorRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.EscriuRecord;

import java.time.LocalDate;
import java.util.List;

public class EscriuTableMemory {
    private final List<EscriuRecord> escriuRecordList = List.of(
            new EscriuRecord("9788433915689", 0),
            new EscriuRecord("9788433999030", 1),
            new EscriuRecord("9788433999542", 1),
            new EscriuRecord("9788499309255", 2),
            new EscriuRecord("9788497937306", 2),
            new EscriuRecord("9788497937856", 2)
    );

    public List<EscriuRecord> get(){
        return escriuRecordList;
    }
}
