package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.ValoracioRecord;

import java.util.ArrayList;
import java.util.List;

public class ValoracioMapper implements Mapper<ValoracioRecord, Valoracio> {
    public Valoracio map(ValoracioRecord valoracioRecord) {
        if (valoracioRecord == null) {
            return null;
        }

        Valoracio valoracio = new Valoracio();
        valoracio.setIsbn(valoracioRecord.getIsbn());
        valoracio.setUsername(valoracioRecord.getUsername());
        valoracio.setData(valoracioRecord.getDate());
        valoracio.setValoracio(valoracioRecord.getValoracio());

        return valoracio;
    }

    public List<Valoracio> map(List<ValoracioRecord> valoracioRecordList){
        if (valoracioRecordList == null) {
            return null;
        }

        return valoracioRecordList.stream().collect(
                ArrayList::new,
                (list, llibreRecord) -> list.add(map(llibreRecord)),
                ArrayList::addAll
        );
    }
}
