package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreGenereDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.GenereTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.LlibreGenereTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.LlibreTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.LlibreGenereRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper.GenereMapper;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper.LlibreMapper;

import java.util.List;

public class LlibreGenereDaoMemory implements LlibreGenereDao {
    private final LlibreGenereTableMemory llibreGenereTableMemory;
    private final LlibreTableMemory llibreTableMemory;
    private final GenereTableMemory genereTableMemory;
    private final LlibreMapper llibreMapper;
    private final GenereMapper genereMapper;

    public LlibreGenereDaoMemory(LlibreGenereTableMemory llibreGenereTableMemory, LlibreTableMemory llibreTableMemory, GenereTableMemory genereTableMemory) {
        this.llibreGenereTableMemory = llibreGenereTableMemory;
        this.llibreTableMemory = llibreTableMemory;
        this.genereTableMemory = genereTableMemory;
        this.llibreMapper = new LlibreMapper();
        this.genereMapper = new GenereMapper();
    }

    @Override
    public List<Llibre> findLlibresByGenereId(int id) {
        List<LlibreGenereRecord> llibreGenereRecords = llibreGenereTableMemory.get().stream()
                .filter(llibreGenereRecord -> llibreGenereRecord.getGenereId() == id)
                .toList();
        return llibreTableMemory.get().stream()
                .filter(llibreRecord -> llibreGenereRecords.stream()
                        .anyMatch(llibreGenereRecord -> llibreGenereRecord.getIsbn().equals(llibreRecord.getIsbn())))
                .map(llibreMapper::map)
                .toList();
    }

    @Override
    public List<Genere> findGeneresByLlibreIsbn(String isbn) {
        List<LlibreGenereRecord> llibreGenereRecords = llibreGenereTableMemory.get().stream()
                .filter(llibreGenereRecord -> llibreGenereRecord.getIsbn().equals(isbn))
                .toList();
        return genereTableMemory.get().stream()
                .filter(genereRecord -> llibreGenereRecords.stream()
                        .anyMatch(llibreGenereRecord -> llibreGenereRecord.getGenereId() == genereRecord.getId()))
                .map(genereMapper::map)
                .toList();
    }
}
