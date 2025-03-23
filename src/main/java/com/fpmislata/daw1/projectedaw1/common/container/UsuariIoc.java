package com.fpmislata.daw1.projectedaw1.common.container;

import com.fpmislata.daw1.projectedaw1.domain.service.UsuariService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.UsuariServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.UsuariDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.UsuariDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.repository.UsuariRepository;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.UsuariRepositoryImpl;

public class UsuariIoc {
    private static UsuariService usuariService;
    private static UsuariRepository usuariRepository;
    private static UsuariDao usuariDao;

    public static UsuariService createService() {
        if (usuariService == null) {
            usuariService = new UsuariServiceImpl(createRepository());
        }
        return usuariService;
    }

    public static UsuariRepository createRepository() {
        if (usuariRepository == null) {
            usuariRepository = new UsuariRepositoryImpl(createDao());
        }
        return usuariRepository;
    }

    public static UsuariDao createDao() {
        if (usuariDao == null) {
            usuariDao = new UsuariDaoJdbc();
        }
        return usuariDao;
    }
}
