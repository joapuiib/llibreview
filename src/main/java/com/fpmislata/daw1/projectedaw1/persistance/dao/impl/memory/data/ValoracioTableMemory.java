package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data;

import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.ValoracioRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ValoracioTableMemory {
    private final List<ValoracioRecord> valoracioRecordList = new ArrayList<>(List.of(
            new ValoracioRecord("9788433915689", "user1", 9, LocalDate.parse("2024-05-01")),
            new ValoracioRecord("9788433915689", "user2", 9, LocalDate.parse("2024-05-01")),
            new ValoracioRecord("9788433915689", "admin", 10, LocalDate.parse("2024-05-01")),
            new ValoracioRecord("9788433999030", "user1", 8, LocalDate.parse("2024-05-01")),
            new ValoracioRecord("9788433999542", "user1", 7, LocalDate.parse("2024-05-01")),
            new ValoracioRecord("9788433999542", "user2", 8, LocalDate.parse("2024-05-01")),
            new ValoracioRecord("9788499309255", "user1", 3, LocalDate.parse("2024-05-01"))
    ));

    public List<ValoracioRecord> get(){
        return valoracioRecordList;
    }

    public void add(ValoracioRecord record){
        valoracioRecordList.add(record);
    }

    public void delete(String isbn, String idUser){
        for (int i = 0; i < valoracioRecordList.size(); i++) {
            if (valoracioRecordList.get(i).getIsbn().equals(isbn) && valoracioRecordList.get(i).getUsername().equals(idUser)) {
                valoracioRecordList.remove(i);
                break;
            }
        }
    }
}
