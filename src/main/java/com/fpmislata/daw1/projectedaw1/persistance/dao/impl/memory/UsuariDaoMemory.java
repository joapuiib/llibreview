package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory;

import com.fpmislata.daw1.projectedaw1.common.utils.EncryptionUtils;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.persistance.dao.UsuariDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.UsuariTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.UsuariRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper.UsuariMapper;

public class UsuariDaoMemory implements UsuariDao {

    private final UsuariTableMemory usuariTableMemory;
    private final UsuariMapper usuariMapper = new UsuariMapper();

    public UsuariDaoMemory(UsuariTableMemory usuariTableMemory) {
        this.usuariTableMemory = usuariTableMemory;
    }

    @Override
    public Usuari findByUsername(String username) {
        return usuariTableMemory.get().stream()
                .filter(usuariRecord -> usuariRecord.getUsername().equals(username))
                .map(usuariMapper::map)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Usuari findByEmail(String email) {
        return usuariTableMemory.get().stream()
                .filter(usuariRecord -> usuariRecord.getEmail().equals(email))
                .map(usuariMapper::map)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void create(Usuari usuari, String password) {
        UsuariRecord usuariRecord = new UsuariRecord();
        usuariRecord.setUsername(usuari.getUsername());
        usuariRecord.setEmail(usuari.getEmail());
        usuariRecord.setDataRegistre(usuari.getDataRegistre());
        usuariRecord.setHashedPassword(EncryptionUtils.hashPassword(password));
        usuariTableMemory.add(usuariRecord);
    }

    @Override
    public boolean login(String username, String password) {
        UsuariRecord user = usuariTableMemory.get().stream()
                .filter(usuariRecord -> usuariRecord.getUsername().equals(username))
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
