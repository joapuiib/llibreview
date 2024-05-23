package com.fpmislata.daw1.projectedaw1.common.container;

import com.fpmislata.daw1.projectedaw1.persistance.dao.EscriuDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.EscriuDaoJdbc;

public class EscriuIoc {
    private static EscriuDao escriuDao;
    public static EscriuDao createDao() {
        if (escriuDao == null) {
            escriuDao = new EscriuDaoJdbc();
        }
        return escriuDao;
    }
}
