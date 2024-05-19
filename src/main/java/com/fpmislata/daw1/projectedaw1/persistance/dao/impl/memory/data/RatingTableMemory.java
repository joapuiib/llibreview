package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data;

import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.RatingRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RatingTableMemory {
    private final List<RatingRecord> ratingRecordList = new ArrayList<>(List.of(
            new RatingRecord("9788433915689", "user1", 9, LocalDate.parse("2024-05-01")),
            new RatingRecord("9788433915689", "user2", 9, LocalDate.parse("2024-05-01")),
            new RatingRecord("9788433915689", "admin", 10, LocalDate.parse("2024-05-01")),
            new RatingRecord("9788433999030", "user1", 8, LocalDate.parse("2024-05-01")),
            new RatingRecord("9788433999542", "user1", 7, LocalDate.parse("2024-05-01")),
            new RatingRecord("9788433999542", "user2", 8, LocalDate.parse("2024-05-01"))
    ));

    public List<RatingRecord> get(){
        return ratingRecordList;
    }

    public void add(RatingRecord record){
        ratingRecordList.add(record);
    }

    public void delete(String isbn, String idUser){
        for (int i = 0; i < ratingRecordList.size(); i++) {
            if (ratingRecordList.get(i).getIsbn().equals(isbn) && ratingRecordList.get(i).getUsername().equals(idUser)) {
                ratingRecordList.remove(i);
                break;
            }
        }
    }
}
