package com.fpmislata.daw1.projectedaw1.common.container;

import com.fpmislata.daw1.projectedaw1.common.AppPropertiesReader;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreGenereDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.LlibreGenereDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.LlibreGenereDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.GenereTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.LlibreGenereTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.LlibreTableMemory;

public class LlibreGenereIoc {
    private static LlibreGenereDao llibreGenereDao;
    private static LlibreGenereTableMemory llibreGenereTableMemory;

    public static LlibreGenereDao createDao() {
        if (llibreGenereDao == null) {
            if(AppPropertiesReader.getProperty("dao").equals("jdbc"))
                llibreGenereDao = new LlibreGenereDaoJdbc();
            else {
                LlibreGenereTableMemory llibreGenereTableMemory = createTableMemory();
                LlibreTableMemory llibreTableMemory = LlibreIoc.createTableMemory();
                GenereTableMemory genereTableMemory = GenereIoc.createTableMemory();
                llibreGenereDao = new LlibreGenereDaoMemory(llibreGenereTableMemory, llibreTableMemory, genereTableMemory);
            }
        }
        return llibreGenereDao;
    }

    public static LlibreGenereTableMemory createTableMemory() {
        if (llibreGenereTableMemory == null) {
            llibreGenereTableMemory = new LlibreGenereTableMemory();
        }
        return llibreGenereTableMemory;
    }
}
