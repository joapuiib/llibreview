package com.fpmislata.daw1.projectedaw1.common.container;

import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreGenereDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.LlibreGenereDaoJdbc;

public class LlibreGenereIoc {
    private static LlibreGenereDao llibreGenereDao;
    public static LlibreGenereDao createDao() {
        if (llibreGenereDao == null) {
            llibreGenereDao = new LlibreGenereDaoJdbc();
        }
        return llibreGenereDao;
    }
}
