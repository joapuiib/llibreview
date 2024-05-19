package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data;

import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.ReviewRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReviewTableMemory {
    private final List<ReviewRecord> reviewRecordList = new ArrayList<>(List.of(
            new ReviewRecord("9788433915689", "user1", LocalDate.parse("2024-05-01"), 7)
    ));

    public List<ReviewRecord> get(){
        return reviewRecordList;
    }

    public void add(ReviewRecord record){
        reviewRecordList.add(record);
    }
}
