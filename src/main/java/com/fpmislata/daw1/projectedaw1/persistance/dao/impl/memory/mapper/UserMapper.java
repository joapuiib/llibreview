package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.User;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.AutorRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.UserRecord;

import java.util.ArrayList;
import java.util.List;

public class UserMapper implements Mapper<UserRecord, User> {
    public User map(UserRecord userRecord) {
        if (userRecord == null) {
            return null;
        }

        User user = new User();
        user.setUsername(userRecord.getUsername());
        user.setEmail(userRecord.getEmail());
        user.setDataRegistre(userRecord.getDataRegistre());

        return user;
    }

    public List<User> map(List<UserRecord> userRecordList) {
        if (userRecordList == null) {
            return null;
        }

        return userRecordList.stream().collect(
                ArrayList::new,
                (list, userRecord) -> list.add(map(userRecord)),
                ArrayList::addAll
        );
    }
}
