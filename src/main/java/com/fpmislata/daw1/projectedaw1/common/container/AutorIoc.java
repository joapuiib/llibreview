package com.fpmislata.daw1.projectedaw1.common.container;

import com.fpmislata.daw1.projectedaw1.domain.service.AutorService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.AutorServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.AutorDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.AutorDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.repository.AutorRepository;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.AutorRepositoryImpl;

public class AutorIoc {
    private static AutorService autorService;

    private static AutorRepository autorRepository;
    private static AutorDao autorDao;

    public static AutorService createService() {
        if (autorService == null) {
            AutorRepository autorRepository = createRepository();
            autorService = new AutorServiceImpl(autorRepository);
        }
        return autorService;
    }

    public static AutorRepository createRepository() {
        if (autorRepository == null) {
            AutorDao autorDao = createAutorDao();
            autorRepository = new AutorRepositoryImpl(autorDao);
        }
        return autorRepository;
    }

    public static AutorDao createAutorDao() {
        if (autorDao == null) {
            autorDao = new AutorDaoMemory();
        }
        return autorDao;
    }
}
