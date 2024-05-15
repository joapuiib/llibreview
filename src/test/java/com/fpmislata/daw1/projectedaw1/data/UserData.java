package com.fpmislata.daw1.projectedaw1.data;

import com.fpmislata.daw1.projectedaw1.domain.entity.User;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.UserRecord;

import java.time.LocalDate;
import java.util.List;

public class UserData {
    public static final List<User> USER_LIST = List.of(
            new User("admin", "admin@localhost", LocalDate.parse("2021-01-01")),
            new User("user", "user@localhost", LocalDate.parse("2021-01-01")),
            new User("user2", "user2@localhost", LocalDate.parse("2021-01-01"))
    );

    public static final List<UserRecord> USER_RECORD_LIST = List.of(
            new UserRecord("admin", "admin@localhost", LocalDate.parse("2021-01-01"), "admin"),
            new UserRecord("user", "user@localhost", LocalDate.parse("2021-01-01"), "user"),
            new UserRecord("user2", "user2@localhost", LocalDate.parse("2021-01-01"), "user2")
    );
}
