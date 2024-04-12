package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.LlibreTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.LlibreRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper.LlibreMapper;

import java.util.List;

public class LlibreDaoMemory implements LlibreDao {

    private final LlibreTableMemory llibreTableMemory = new LlibreTableMemory();
    private final LlibreMapper llibreMapper = new LlibreMapper();

    @Override
    public List<Llibre> findAll() {
        List<LlibreRecord> llibreRecordList = llibreTableMemory.selectAll();
        return llibreMapper.map(llibreRecordList);
    }

    @Override
    public Llibre findByIsbn(String isbn) {
        LlibreRecord llibreRecord = llibreTableMemory.select(isbn);
        return llibreMapper.map(llibreRecord);
    }

    @Override
    public List<Llibre> findLatest(int n) {
        List<Llibre> llibreList = this.findAll();
        llibreList.sort((l1, l2) -> l2.getDataPublicacio().compareTo(l1.getDataPublicacio()));
        n = Math.min(n, llibreList.size());
        return llibreList.subList(0, n);
    }

    @Override
    public List<Llibre> findMostRead(int n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Llibre> findBestReview(int n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean insert(Llibre llibre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Llibre delete(String isbn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
