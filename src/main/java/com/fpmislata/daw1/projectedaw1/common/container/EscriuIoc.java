package com.fpmislata.daw1.projectedaw1.common.container;

import com.fpmislata.daw1.projectedaw1.common.AppPropertiesReader;
import com.fpmislata.daw1.projectedaw1.persistance.dao.EscriuDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.EscriuDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.EscriuDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.AutorTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.EscriuTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.LlibreTableMemory;

public class EscriuIoc {
    private static EscriuDao escriuDao;
    private static EscriuTableMemory escriuTableMemory;

    public static EscriuDao createDao() {
        if (escriuDao == null) {
            if(AppPropertiesReader.getProperty("dao").equals("jdbc"))
                escriuDao = new EscriuDaoJdbc();
            else {
                LlibreTableMemory llibreTableMemory = LlibreIoc.createTableMemory();
                AutorTableMemory autorTableMemory = AutorIoc.createTableMemory();
                EscriuTableMemory escriuTableMemory = createTableMemory();
                escriuDao = new EscriuDaoMemory(escriuTableMemory, llibreTableMemory, autorTableMemory);
            }
        }
        return escriuDao;
    }

    public static EscriuTableMemory createTableMemory() {
        if (escriuTableMemory == null) {
            escriuTableMemory = new EscriuTableMemory();
        }
        return escriuTableMemory;
    }
}
