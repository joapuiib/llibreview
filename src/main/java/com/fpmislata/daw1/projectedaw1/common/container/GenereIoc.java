package com.fpmislata.daw1.projectedaw1.common.container;

import com.fpmislata.daw1.projectedaw1.common.AppPropertiesReader;
import com.fpmislata.daw1.projectedaw1.domain.service.GenereService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.GenereServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.GenereDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.GenereDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.GenereDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.GenereTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.repository.GenereRepository;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.GenereRepositoryImpl;

public class GenereIoc {
    private static GenereService genereService;
    private static GenereRepository genereRepository;
    private static GenereDao genereDao;
    private static GenereTableMemory genereTableMemory;

    public static GenereService createService() {
        if (genereService == null) {
            GenereRepository genereRepository = createRepository();
            genereService = new GenereServiceImpl(genereRepository);
        }
        return genereService;
    }

    public static GenereRepository createRepository() {
        if (genereRepository == null) {
            GenereDao genereDao = createDao();
            genereRepository = new GenereRepositoryImpl(genereDao, null);
        }
        return genereRepository;
    }

    public static GenereDao createDao() {
        if (genereDao == null) {
            if(AppPropertiesReader.getProperty("dao").equals("jdbc"))
                genereDao = new GenereDaoJdbc();
            else {
                GenereTableMemory genereTableMemory = createTableMemory();
                genereDao = new GenereDaoMemory(genereTableMemory);
            }
        }
        return genereDao;
    }

    public static GenereTableMemory createTableMemory() {
        if (genereTableMemory == null) {
            genereTableMemory = new GenereTableMemory();
        }
        return genereTableMemory;
    }
}
