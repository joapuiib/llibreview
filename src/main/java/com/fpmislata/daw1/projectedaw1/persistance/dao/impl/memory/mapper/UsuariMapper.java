package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.UsuariRecord;

public class UsuariMapper extends Mapper<UsuariRecord, Usuari> {
    @Override
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
}
