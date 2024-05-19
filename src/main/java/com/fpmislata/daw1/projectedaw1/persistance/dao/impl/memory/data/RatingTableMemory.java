package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data;

import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.RatingRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RatingTableMemory {
    private final List<RatingRecord> ratingRecordList = new ArrayList<>(List.of(
            new RatingRecord("9788433915689", "user1", LocalDate.parse("2024-05-01"), 7)
    ));

    public List<RatingRecord> get(){
        return ratingRecordList;
    }

    public void add(RatingRecord record){
        ratingRecordList.add(record);
    }
}
