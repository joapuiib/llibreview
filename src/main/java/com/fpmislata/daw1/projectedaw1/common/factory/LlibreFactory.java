package com.fpmislata.daw1.projectedaw1.common.factory;

import com.fpmislata.daw1.projectedaw1.persistance.database.DatabaseConnection;
import com.fpmislata.daw1.projectedaw1.domain.service.LlibreService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.LlibreServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.LlibreDaoImpl;
import com.fpmislata.daw1.projectedaw1.persistance.repository.LlibreRepository;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.LlibreRepositoryImpl;

public class LlibreFactory {
    private static LlibreService llibraService;
    private static LlibreRepository llibreRepository;
    private static LlibreDao llibreDao;

    public static LlibreService createLlibreService() {
        if (llibraService == null) {
            LlibreRepository llibreRepository = createLlibreRepository();
            llibraService = new LlibreServiceImpl(llibreRepository);
        }
        return llibraService;
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
            DatabaseConnection connection = DatabaseConnection.getInstance();
            llibreDao = new LlibreDaoImpl(connection);
        }
        return llibreDao;
    }
}
