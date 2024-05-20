package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.UsuariRecord;

import java.util.ArrayList;
import java.util.List;

public class UsuariMapper implements Mapper<UsuariRecord, Usuari> {
    public Usuari map(UsuariRecord usuariRecord) {
        if (usuariRecord == null) {
            return null;
        }

        Usuari usuari = new Usuari();
        usuari.setUsername(usuariRecord.getUsername());
        usuari.setEmail(usuariRecord.getEmail());
        usuari.setDataRegistre(usuariRecord.getDataRegistre());

        return usuari;
    }

    public List<Usuari> map(List<UsuariRecord> usuariRecordList) {
        if (usuariRecordList == null) {
            return null;
        }

        return usuariRecordList.stream().collect(
                ArrayList::new,
                (list, usuariRecord) -> list.add(map(usuariRecord)),
                ArrayList::addAll
        );
    }
}
