package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.GenereRecord;

import java.util.ArrayList;
import java.util.List;

public class GenereMapper implements Mapper<GenereRecord, Genere> {
    public Genere map(GenereRecord genereRecord) {
        if (genereRecord == null) {
            return null;
        }

        Genere genere = new Genere();
        genere.setId(genereRecord.getId());
        genere.setNom(genereRecord.getNom_ca());
        genere.setRutaImatge(genereRecord.getRutaImatge());

        return genere;
    }

    public List<Genere> map(List<GenereRecord> genereRecordList){
        if (genereRecordList == null) {
            return null;
        }

        return genereRecordList.stream().collect(
                ArrayList::new,
                (list, genereRecord) -> list.add(map(genereRecord)),
                ArrayList::addAll
        );
    }
}
