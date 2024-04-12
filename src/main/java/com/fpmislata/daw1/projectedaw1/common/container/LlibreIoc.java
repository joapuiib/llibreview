package com.fpmislata.daw1.projectedaw1.common.container;

import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.LlibreDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.LlibreDaoMemory;
import com.fpmislata.daw1.projectedaw1.domain.service.LlibreService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.LlibreServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreDao;
import com.fpmislata.daw1.projectedaw1.persistance.repository.LlibreRepository;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.LlibreRepositoryImpl;

public class LlibreIoc {
    private static LlibreService llibreService;
    private static LlibreRepository llibreRepository;
    private static LlibreDao llibreDao;

    public static LlibreService createLlibreService() {
        if (llibreService == null) {
            LlibreRepository llibreRepository = createLlibreRepository();
            llibreService = new LlibreServiceImpl(llibreRepository);
        }
        return llibreService;
    }

    public static LlibreRepository createLlibreRepository() {
        if (llibreRepository == null) {
            LlibreDao llibreDao = createLlibreDao();
            llibreRepository = new LlibreRepositoryImpl(llibreDao);
        }
        return llibreRepository;
    }

    public static LlibreDao createLlibreDao() {
        if (llibreDao == null) {
            llibreDao = new LlibreDaoJdbc();
        }
        return llibreDao;
    }
}
