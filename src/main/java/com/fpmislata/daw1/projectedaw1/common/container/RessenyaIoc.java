package com.fpmislata.daw1.projectedaw1.common.container;

import com.fpmislata.daw1.projectedaw1.domain.service.RessenyaService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.RessenyaServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.RessenyaDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.RessenyaDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.repository.RessenyaRepository;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.RessenyaRepositoryImpl;

public class RessenyaIoc {
    private static RessenyaService ressenyaService;
    private static RessenyaRepository ressenyaRepository;
    private static RessenyaDao ressenyaDao;

    public static RessenyaService createService() {
        if (ressenyaService == null) {
            RessenyaRepository ressenyaRepository = createRepository();
            ressenyaService = new RessenyaServiceImpl(ressenyaRepository);
        }
        return ressenyaService;
    }

    public static RessenyaRepository createRepository() {
        if (ressenyaRepository == null) {
            RessenyaDao ressenyaDao = createDao();
            ressenyaRepository = new RessenyaRepositoryImpl(ressenyaDao);
        }
        return ressenyaRepository;
    }

    public static RessenyaDao createDao() {
        if (ressenyaDao == null)
            ressenyaDao = new RessenyaDaoJdbc();
        return ressenyaDao;
    }
}
