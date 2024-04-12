package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.LlibreRecord;

import java.util.ArrayList;
import java.util.List;

public class LlibreMapper implements Mapper<LlibreRecord, Llibre> {
    public Llibre map(LlibreRecord llibreRecord) {
        if (llibreRecord == null) {
            return null;
        }

        Llibre llibre = new Llibre();
        llibre.setIsbn(llibreRecord.getIsbn());
        llibre.setTitol(llibreRecord.getTitol_ca());
        llibre.setResum(llibreRecord.getResum_ca());
        llibre.setDataPublicacio(llibreRecord.getDataPublicacio());
        llibre.setNombrePagines(llibreRecord.getNombrePagines());
        llibre.setRutaImatge(llibreRecord.getRutaImatge());

        return llibre;
    }

    public List<Llibre> map(List<LlibreRecord> llibreRecordList){
        if (llibreRecordList == null) {
            return null;
        }

        return llibreRecordList.stream().collect(
                ArrayList::new,
                (list, llibreRecord) -> list.add(map(llibreRecord)),
                ArrayList::addAll
        );
    }
}
