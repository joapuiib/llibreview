package com.fpmislata.daw1.projectedaw1.common.container;

import com.fpmislata.daw1.projectedaw1.common.AppPropertiesReader;
import com.fpmislata.daw1.projectedaw1.persistance.dao.EscriuDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.EscriuDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.EscriuDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.EscriuTableMemory;

public class EscriuIoc {
    private static EscriuDao escriuDao;

    public static EscriuDao createDao() {
        if (escriuDao == null) {
            if(AppPropertiesReader.getProperty("dao").equals("jdbc"))
                escriuDao = new EscriuDaoJdbc();
            else
                escriuDao = new EscriuDaoMemory(new EscriuTableMemory());
        }
        return escriuDao;
    }
}
