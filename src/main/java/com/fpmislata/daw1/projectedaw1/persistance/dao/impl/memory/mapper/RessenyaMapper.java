package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.RessenyaRecord;

public class RessenyaMapper extends Mapper<RessenyaRecord, Ressenya> {
    public Ressenya map(RessenyaRecord ressenyaRecord) {
        if (ressenyaRecord == null) {
            return null;
        }

        Ressenya ressenya = new Ressenya();
        ressenya.setIsbn(ressenyaRecord.getIsbn());
        ressenya.setUsername(ressenyaRecord.getUsername());
        ressenya.setComentari(ressenyaRecord.getComentari());
        ressenya.setData(ressenyaRecord.getData());

        return ressenya;
    }
}
