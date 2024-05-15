package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record;

import com.fpmislata.daw1.projectedaw1.common.Utils;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRecord {
    private String username;
    private String email;
    private LocalDate dataRegistre;
    private String hashedPassword;

    public UserRecord(String username, String email, LocalDate dataRegistre, String password) {
        this.username = username;
        this.email = email;
        this.dataRegistre = dataRegistre;
        this.hashedPassword = Utils.hashPassword(password);
    }

    public UserRecord() {
    }
}
