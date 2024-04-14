package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.persistance.dao.AutorDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.AutorTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.EscriuTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.AutorRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper.AutorMapper;

import java.util.List;

public class AutorDaoMemory implements AutorDao {

    private final AutorTableMemory autorTableMemory = new AutorTableMemory();
    private final EscriuTableMemory escriuTableMemory = new EscriuTableMemory();
    private final AutorMapper autorMapper = new AutorMapper();

    @Override
    public Autor findById(int id) {
        List<AutorRecord> autorRecordList = autorTableMemory.get();
        return autorRecordList.stream()
                .filter(autorRecord -> autorRecord.getId() == id)
                .map(autorMapper::map)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Autor> findAll() {
        return autorTableMemory.get().stream()
                .map(autorMapper::map)
                .toList();
    }
}
