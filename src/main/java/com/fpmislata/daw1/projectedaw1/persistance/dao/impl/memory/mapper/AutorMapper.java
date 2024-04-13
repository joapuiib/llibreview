package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.AutorRecord;

import java.util.ArrayList;
import java.util.List;

public class AutorMapper implements Mapper<AutorRecord, Autor> {
    public Autor map(AutorRecord autorRecord) {
        if (autorRecord == null) {
            return null;
        }

        Autor autor = new Autor();
        autor.setId(autorRecord.getId());
        autor.setNom(autorRecord.getNom());
        autor.setBiografia(autorRecord.getBiografia());
        autor.setDataNaixement(autorRecord.getDataNaixement());
        autor.setRutaImatge(autorRecord.getRutaImatge());

        return autor;
    }

    public List<Autor> map(List<AutorRecord> autorRecordList) {
        if (autorRecordList == null) {
            return null;
        }

        return autorRecordList.stream().collect(
                ArrayList::new,
                (list, llibreRecord) -> list.add(map(llibreRecord)),
                ArrayList::addAll
        );
    }
}
