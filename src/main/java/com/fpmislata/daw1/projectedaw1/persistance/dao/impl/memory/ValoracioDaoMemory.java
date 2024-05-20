package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory;

import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.persistance.dao.ValoracioDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.ValoracioTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.ValoracioRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper.ValoracioMapper;

import java.util.List;

public class ValoracioDaoMemory implements ValoracioDao {

    private final ValoracioTableMemory valoracioTableMemory;
    private final ValoracioMapper valoracioMapper = new ValoracioMapper();

    public ValoracioDaoMemory(ValoracioTableMemory valoracioTableMemory) {
        this.valoracioTableMemory = valoracioTableMemory;
    }

    @Override
    public Valoracio findByLlibreIsbnAndUsername(String isbn, String username) {
        return valoracioTableMemory.get().stream()
                .filter(valoracioRecord -> valoracioRecord.getIsbn().equals(isbn) && valoracioRecord.getUsername().equals(username))
                .map(valoracioMapper::map)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Valoracio> findByLlibreIsbn(String isbn) {
        return valoracioTableMemory.get().stream()
                .filter(valoracioRecord -> valoracioRecord.getIsbn().equals(isbn))
                .map(valoracioMapper::map)
                .toList();
    }

    @Override
    public List<Valoracio> findByUsername(String username) {
        return valoracioTableMemory.get().stream()
                .filter(valoracioRecord -> valoracioRecord.getUsername().equals(username))
                .map(valoracioMapper::map)
                .toList();
    }

    @Override
    public void save(Valoracio valoracio) {
        if (findByLlibreIsbnAndUsername(valoracio.getIsbn(), valoracio.getUsername()) != null) {
            update(valoracio);
        } else {
            insert(valoracio);
        }
    }

    @Override
    public void insert(Valoracio valoracio) {
        ValoracioRecord valoracioRecord = new ValoracioRecord();
        valoracioRecord.setIsbn(valoracio.getIsbn());
        valoracioRecord.setUsername(valoracio.getUsername());
        valoracioRecord.setValoracio(valoracio.getValoracio());
        valoracioRecord.setDate(valoracio.getData());
        valoracioTableMemory.add(valoracioRecord);
    }

    public void update(Valoracio valoracio) {
        ValoracioRecord valoracioRecord = valoracioTableMemory.get().stream()
                .filter(valoraciorecord -> valoraciorecord.getIsbn().equals(valoracio.getIsbn()) && valoraciorecord.getUsername().equals(valoracio.getUsername()))
                .findFirst()
                .orElseThrow();
        valoracioRecord.setValoracio(valoracio.getValoracio());
        valoracioRecord.setDate(valoracio.getData());
    }

    @Override
    public void delete(String isbn, String username) {
        valoracioTableMemory.delete(isbn, username);
    }
}
