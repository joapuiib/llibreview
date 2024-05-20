package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record;

import com.fpmislata.daw1.projectedaw1.common.utils.EncryptionUtils;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuariRecord {
    private String username;
    private String email;
    private LocalDate dataRegistre;
    private String hashedPassword;

    public UsuariRecord(String username, String email, LocalDate dataRegistre, String password) {
        this.username = username;
        this.email = email;
        this.dataRegistre = dataRegistre;
        this.hashedPassword = EncryptionUtils.hashPassword(password);
    }

    public UsuariRecord() {
    }
}
