package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data;

import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.LlibreGenereRecord;

import java.util.List;

public class LlibreGenereTableMemory {
    private final List<LlibreGenereRecord> llibreGenereRecordList = List.of(
            new LlibreGenereRecord("9788433915689", 1),
            new LlibreGenereRecord("9788433915689", 2),
            new LlibreGenereRecord("9788433999030", 2),
            new LlibreGenereRecord("9788433999542", 2),
            new LlibreGenereRecord("9788499309255", 3),
            new LlibreGenereRecord("9788497937306", 3),
            new LlibreGenereRecord("9788497937856", 3)
    );

    public List<LlibreGenereRecord> get(){
        return llibreGenereRecordList;
    }
}
