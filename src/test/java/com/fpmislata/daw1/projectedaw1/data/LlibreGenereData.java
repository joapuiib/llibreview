package com.fpmislata.daw1.projectedaw1.data;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.GenereRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.LlibreGenereRecord;

import java.util.List;

public class LlibreGenereData {
    public static final List<LlibreGenereRecord> LLIBRE_GENERE_RECORD_LIST = List.of(
            new LlibreGenereRecord("isbn1", 1),
            new LlibreGenereRecord("isbn1", 2),
            new LlibreGenereRecord("isbn2", 1)
    );
}
