package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.persistance.dao.GenereDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.GenereTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.GenereRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper.GenereMapper;

import java.util.List;

public class GenereDaoMemory implements GenereDao {

    private final GenereTableMemory genereTableMemory;
    private final GenereMapper genereMapper = new GenereMapper();

    public GenereDaoMemory(GenereTableMemory genereTableMemory) {
        this.genereTableMemory = genereTableMemory;
    }

    @Override
    public Genere findById(int id) {
        List<GenereRecord> genereRecordList = genereTableMemory.get();
        return genereRecordList.stream()
                .filter(genereRecord -> genereRecord.getId() == id)
                .map(genereMapper::map)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Genere> findAll() {
        List<GenereRecord> genereRecordList = genereTableMemory.get();
        return genereMapper.map(genereRecordList);
    }
}
