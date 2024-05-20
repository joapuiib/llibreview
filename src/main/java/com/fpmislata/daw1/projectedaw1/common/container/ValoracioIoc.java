package com.fpmislata.daw1.projectedaw1.common.container;

import com.fpmislata.daw1.projectedaw1.common.AppPropertiesReader;
import com.fpmislata.daw1.projectedaw1.domain.service.ValoracioService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.ValoracioServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.ValoracioDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.ValoracioDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.ValoracioDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.ValoracioTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.repository.ValoracioRepository;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.ValoracioRepositoryImpl;

public class ValoracioIoc {
    private static ValoracioService valoracioService;
    private static ValoracioRepository valoracioRepository;
    private static ValoracioDao valoracioDao;
    private static ValoracioTableMemory valoracioTableMemory;

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
            if (AppPropertiesReader.getProperty("dao").equals("jdbc"))
                valoracioDao = new ValoracioDaoJdbc();
            else {
                ValoracioTableMemory valoracioTableMemory = createTableMemory();
                valoracioDao = new ValoracioDaoMemory(valoracioTableMemory);
            }
        }
        return valoracioDao;
    }

    public static ValoracioTableMemory createTableMemory() {
        if (valoracioTableMemory == null)
            valoracioTableMemory = new ValoracioTableMemory();
        return valoracioTableMemory;
    }
}
