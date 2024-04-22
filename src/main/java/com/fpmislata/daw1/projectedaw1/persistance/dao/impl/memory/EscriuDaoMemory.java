package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.EscriuDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.AutorTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.EscriuTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.LlibreTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.AutorRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.EscriuRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.LlibreRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper.AutorMapper;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper.LlibreMapper;

import java.util.List;

public class EscriuDaoMemory implements EscriuDao {
    private final EscriuTableMemory escriuTableMemory;
    private final AutorTableMemory autorTableMemory;
    private final LlibreTableMemory llibreTableMemory;
    private final AutorMapper autorMapper;
    private final LlibreMapper llibreMapper;

    public EscriuDaoMemory(EscriuTableMemory escriuTableMemory, LlibreTableMemory llibreTableMemory, AutorTableMemory autorTableMemory) {
        this.escriuTableMemory = escriuTableMemory;
        this.autorTableMemory = autorTableMemory;
        this.llibreTableMemory = llibreTableMemory;
        this.autorMapper = new AutorMapper();
        this.llibreMapper = new LlibreMapper();
    }

    @Override
    public List<Autor> findAutorsByIsbn(String isbn) {
        List<EscriuRecord> escriuRecordList = escriuTableMemory.get();
        List<AutorRecord> result =  autorTableMemory.get().stream()
                .filter(
                        autor -> escriuRecordList.stream()
                        .anyMatch(escriuRecord -> autor.getId() == escriuRecord.getIdAutor() && isbn.equals(escriuRecord.getIsbn())))
                .toList();
        return autorMapper.map(result);
    }

    @Override
    public List<Llibre> findLlibresByAutor(int idAutor) {
        List<EscriuRecord> escriuRecordList = escriuTableMemory.get();
        List<LlibreRecord> result = llibreTableMemory.get().stream()
                .filter(llibre -> escriuRecordList.stream()
                        .anyMatch(escriuRecord -> llibre.getIsbn().equals(escriuRecord.getIsbn()) && idAutor == escriuRecord.getIdAutor()))
                .toList();
        return llibreMapper.map(result);
    }
}
