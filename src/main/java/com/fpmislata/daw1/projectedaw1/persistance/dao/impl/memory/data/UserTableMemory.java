package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data;

import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.UserRecord;

import java.time.LocalDate;
import java.util.List;

public class UserTableMemory {
    private final List<UserRecord> userRecordList = List.of(
            new UserRecord("admin", "admin@localhost", LocalDate.parse("2021-01-01"), "admin"),
            new UserRecord("user", "user@localhost", LocalDate.parse("2021-01-01"), "user"),
            new UserRecord("user2", "user2@localhost", LocalDate.parse("2021-01-01"), "user2")
    );

    public List<UserRecord> get(){
        return userRecordList;
    }
}
