package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data;

import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.GenereRecord;

import java.util.List;

public class GenereTableMemory {
    private final List<GenereRecord> genereRecordList = List.of(
            new GenereRecord(
                    1,
                    "Ficció literària",
                    "Literary fiction",
                    "ficcio_literaria.jpg"
            ),
            new GenereRecord(
                    2,
                    "Narrativa",
                    "Narrative",
                    "narrativa.jpg"
            ),
            new GenereRecord(
                    3,
                    "Ciència-ficció",
                    "Science fiction",
                    "ciencia_ficcio.jpg"
            )
    );

    public List<GenereRecord> get(){
        return genereRecordList;
    }
}
