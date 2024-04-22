package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory;

import com.fpmislata.daw1.projectedaw1.common.container.AutorIoc;
import com.fpmislata.daw1.projectedaw1.common.container.LlibreIoc;
import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.AutorDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.EscriuDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.EscriuTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.EscriuRecord;

import java.util.List;

public class EscriuDaoMemory implements EscriuDao {
    private final EscriuTableMemory escriuTableMemory;

    public EscriuDaoMemory(EscriuTableMemory escriuTableMemory) {
        this.escriuTableMemory = escriuTableMemory;
    }

    @Override
    public List<Autor> findAutorsByIsbn(String isbn) {
        AutorDao autorDao = AutorIoc.createDao();
        List<EscriuRecord> escriuRecordList = escriuTableMemory.get();
        return autorDao.findAll().stream()
                .filter(autor -> escriuRecordList.stream()
                        .anyMatch(escriuRecord -> autor.getId() == escriuRecord.getIdAutor() && isbn.equals(escriuRecord.getIsbn())))
                .toList();
    }

    @Override
    public List<Llibre> findLlibresByAutor(int idAutor) {
        LlibreDao llibreDao = LlibreIoc.createDao();
        List<EscriuRecord> escriuRecordList = escriuTableMemory.get();
        return llibreDao.findAll().stream()
                .filter(llibre -> escriuRecordList.stream()
                        .anyMatch(escriuRecord -> llibre.getIsbn().equals(escriuRecord.getIsbn()) && idAutor == escriuRecord.getIdAutor()))
                .toList();
    }
}
