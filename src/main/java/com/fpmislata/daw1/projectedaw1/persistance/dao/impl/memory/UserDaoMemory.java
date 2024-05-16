package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory;

import com.fpmislata.daw1.projectedaw1.common.Utils;
import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.User;
import com.fpmislata.daw1.projectedaw1.persistance.dao.AutorDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.UserDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.AutorTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.UserTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.AutorRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.UserRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper.AutorMapper;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper.UserMapper;
import com.fpmislata.daw1.projectedaw1.security.UserSession;

import java.time.LocalDate;
import java.util.List;

public class UserDaoMemory implements UserDao {

    private final UserTableMemory userTableMemory;
    private final UserMapper userMapper = new UserMapper();

    public UserDaoMemory(UserTableMemory userTableMemory) {
        this.userTableMemory = userTableMemory;
    }

    @Override
    public User findByUsername(String username) {
        return userTableMemory.get().stream()
                .filter(userRecord -> userRecord.getUsername().equals(username))
                .map(userMapper::map)
                .findFirst()
                .orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userTableMemory.get().stream()
                .filter(userRecord -> userRecord.getEmail().equals(email))
                .map(userMapper::map)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void create(User user, String password) {
        UserRecord userRecord = new UserRecord();
        userRecord.setUsername(user.getUsername());
        userRecord.setHashedPassword(Utils.hashPassword(password));
        userRecord.setEmail(user.getEmail());
        userRecord.setDataRegistre(LocalDate.now());
        userTableMemory.add(userRecord);
    }

    @Override
    public boolean login(String username, String password) {
        UserRecord user = userTableMemory.get().stream()
                .filter(userRecord -> userRecord.getUsername().equals(username))
                .findFirst()
                .orElse(null);

        String errorMessage = "No s'ha trobat l'usuari o la contrasenya és incorrecta.";
        if (user == null) {
            throw new RuntimeException(errorMessage);
        }
        boolean passwordMatch = Utils.checkPassword(password, user.getHashedPassword());
        if (!passwordMatch) {
            throw new RuntimeException(errorMessage);
        }
        return true;
    }
}
