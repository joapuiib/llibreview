package com.fpmislata.daw1.projectedaw1.common.container;

import com.fpmislata.daw1.projectedaw1.common.AppPropertiesReader;
import com.fpmislata.daw1.projectedaw1.domain.service.AutorService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.AutorServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.AutorDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.EscriuDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.AutorDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.AutorDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.AutorTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.repository.AutorRepository;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.AutorRepositoryImpl;

public class AutorIoc {
    private static AutorService autorService;

    private static AutorRepository autorRepository;
    private static AutorDao autorDao;
    private static AutorTableMemory autorTableMemory;

    public static AutorService createService() {
        if (autorService == null) {
            AutorRepository autorRepository = createRepository();
            autorService = new AutorServiceImpl(autorRepository);
        }
        return autorService;
    }

    public static AutorRepository createRepository() {
        if (autorRepository == null) {
            AutorDao autorDao = createDao();
            EscriuDao escriuDao = EscriuIoc.createDao();
            autorRepository = new AutorRepositoryImpl(autorDao, escriuDao);
        }
        return autorRepository;
    }

    public static AutorDao createDao() {
        if (autorDao == null) {
            if(AppPropertiesReader.getProperty("dao").equals("jdbc"))
                autorDao = new AutorDaoJdbc();
            else {
                AutorTableMemory autorTableMemory = createTableMemory();
                autorDao = new AutorDaoMemory(autorTableMemory);
            }
        }
        return autorDao;
    }

    public static AutorTableMemory createTableMemory() {
        if (autorTableMemory == null) {
            autorTableMemory = new AutorTableMemory();
        }
        return autorTableMemory;
    }
}
