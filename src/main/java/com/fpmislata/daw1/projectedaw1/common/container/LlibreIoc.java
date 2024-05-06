package com.fpmislata.daw1.projectedaw1.common.container;

import com.fpmislata.daw1.projectedaw1.common.AppPropertiesReader;
import com.fpmislata.daw1.projectedaw1.persistance.dao.EscriuDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreGenereDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.LlibreDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.LlibreDaoMemory;
import com.fpmislata.daw1.projectedaw1.domain.service.LlibreService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.LlibreServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.LlibreTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.repository.LlibreRepository;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.LlibreRepositoryImpl;

public class LlibreIoc {
    private static LlibreService llibreService;
    private static LlibreRepository llibreRepository;
    private static LlibreDao llibreDao;
    private static LlibreTableMemory llibreTableMemory;

    public static LlibreService createService() {
        if (llibreService == null) {
            LlibreRepository llibreRepository = createRepository();
            llibreService = new LlibreServiceImpl(llibreRepository);
        }
        return llibreService;
    }

    public static LlibreRepository createRepository() {
        if (llibreRepository == null) {
            LlibreDao llibreDao = createDao();
            EscriuDao escriuDao = EscriuIoc.createDao();
            LlibreGenereDao llibreGenereDao = LlibreGenereIoc.createDao();
            llibreRepository = new LlibreRepositoryImpl(llibreDao, escriuDao, llibreGenereDao);
        }
        return llibreRepository;
    }

    public static LlibreDao createDao() {
        if (llibreDao == null) {
            if(AppPropertiesReader.getProperty("dao").equals("jdbc"))
                llibreDao = new LlibreDaoJdbc();
            else {
                LlibreTableMemory llibreTableMemory = createTableMemory();
                llibreDao = new LlibreDaoMemory(llibreTableMemory);
            }
        }
        return llibreDao;
    }

    public static LlibreTableMemory createTableMemory() {
        if (llibreTableMemory == null) {
            llibreTableMemory = new LlibreTableMemory();
        }
        return llibreTableMemory;
    }
}
