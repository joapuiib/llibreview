package com.fpmislata.daw1.projectedaw1.data;

import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.EscriuRecord;

import java.util.List;

public class EscriuData {
    public static final List<EscriuRecord> ESCRIU_RECORD_LIST = List.of(
            new EscriuRecord("isbn2", 1),
            new EscriuRecord("isbn3", 1),
            new EscriuRecord("isbn3", 2)
    );
}
