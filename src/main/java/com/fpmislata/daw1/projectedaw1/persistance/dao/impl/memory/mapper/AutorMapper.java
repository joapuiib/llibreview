package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.AutorRecord;

public class AutorMapper extends Mapper<AutorRecord, Autor> {
    @Override
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
}
