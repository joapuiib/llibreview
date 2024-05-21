package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.GenereRecord;

public class GenereMapper extends Mapper<GenereRecord, Genere> {
    @Override
    public Genere map(GenereRecord genereRecord) {
        if (genereRecord == null) {
            return null;
        }

        Genere genere = new Genere();
        genere.setId(genereRecord.getId());
        genere.setNom(genereRecord.getNom_ca());

        return genere;
    }
}
