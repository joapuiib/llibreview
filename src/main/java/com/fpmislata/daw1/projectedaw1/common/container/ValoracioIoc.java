package com.fpmislata.daw1.projectedaw1.common.container;

import com.fpmislata.daw1.projectedaw1.domain.service.ValoracioService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.ValoracioServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.ValoracioDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.ValoracioDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.repository.ValoracioRepository;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.ValoracioRepositoryImpl;

public class ValoracioIoc {
    private static ValoracioService valoracioService;
    private static ValoracioRepository valoracioRepository;
    private static ValoracioDao valoracioDao;

    public static ValoracioService createService() {
        if (valoracioService == null) {
            ValoracioRepository valoracioRepository = createRepository();
            valoracioService = new ValoracioServiceImpl(valoracioRepository);
        }
        return valoracioService;
    }

    private static ValoracioRepository createRepository() {
        if (valoracioRepository == null) {
            ValoracioDao valoracioDao = createDao();
            valoracioRepository = new ValoracioRepositoryImpl(valoracioDao);
        }
        return valoracioRepository;
    }

    public static ValoracioDao createDao() {
        if (valoracioDao == null) {
            valoracioDao = new ValoracioDaoJdbc();
        }
        return valoracioDao;
    }
}
