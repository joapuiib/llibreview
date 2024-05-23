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

    public static UsuariService getUserService() {
        if (usuariService == null) {
            usuariService = new UsuariServiceImpl(getUserRepository());
        }
        return usuariService;
    }

    public static UsuariRepository getUserRepository() {
        if (usuariRepository == null) {
            usuariRepository = new UsuariRepositoryImpl(getUserDao());
        }
        return usuariRepository;
    }

    public static UsuariDao getUserDao() {
        if (usuariDao == null) {
            usuariDao = new UsuariDaoJdbc();
        }
        return usuariDao;
    }
}
