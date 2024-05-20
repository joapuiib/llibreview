package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory;

import com.fpmislata.daw1.projectedaw1.common.utils.EncryptionUtils;
import com.fpmislata.daw1.projectedaw1.domain.entity.User;
import com.fpmislata.daw1.projectedaw1.persistance.dao.UserDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.UserTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.UserRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper.UserMapper;

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
        userRecord.setEmail(user.getEmail());
        userRecord.setDataRegistre(user.getDataRegistre());
        userRecord.setHashedPassword(EncryptionUtils.hashPassword(password));
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
        boolean passwordMatch = EncryptionUtils.checkPassword(password, user.getHashedPassword());
        if (!passwordMatch) {
            throw new RuntimeException(errorMessage);
        }
        return true;
    }
}
